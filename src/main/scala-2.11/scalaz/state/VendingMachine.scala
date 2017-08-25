package scalaz.state

sealed trait Input
case object Coin extends Input
case object Turn extends Input

case class Machine(locked: Boolean, candies: Int, coins: Int)

import State._
object VendingMachine extends App {
  def update = (i: Input) => (s: Machine) =>
    (i, s) match {
      case (_, Machine(_, 0, _)) => s
      case (Coin, Machine(false, _, _)) => s
      case (Coin, Machine(true, candy, coin)) => Machine(false, candy, coin+1)
      case (Turn, Machine(true, _, _)) => s
      case (Turn, Machine(false, candy, coin)) => Machine(true, candy-1, coin)
    }

  /**
    * updateの返り値はf: Machine => Machine
    * このfをmodifyに渡す
    * 何故返り値が(Int, Int)ではなくてState[Machine, (Int, Int)]なのかわからない
    */
  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = for {
    _ <- sequence(inputs map (modify[Machine] _ compose update))
    s <- get
  } yield (s.coins, s.candies)
}
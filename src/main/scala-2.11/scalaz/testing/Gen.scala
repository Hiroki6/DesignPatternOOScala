package scalaz.testing

import scalaz.state.{RNG, State}
import scalaz.testing.Prop.{FailedCase, SuccessCount}

case class Gen[A](sample: State[RNG, A]) {

  def flatMap[B](f: A => Gen[B]): Gen[B] = ???

  def listOfN(size: Gen[Int]): Gen[List[A]] = ???
}

object Gen {
  def choose(start: Int, stopExclusive: Int): Gen[Int] =
    Gen(State(RNG.nonNegativeInt).map(n => start + n % (stopExclusive - start)))

  def unit[A](a: => A): Gen[A] = Gen(State.unit(a))

  def boolean: Gen[Boolean] = Gen(State(RNG.boolean))

  def listOfN[A](n: Int, g: Gen[A]): Gen[List[A]] = Gen(State.sequence(List.fill(n)(g.sample)))

}

/*
trait Prop {
  def check: Boolean
  def &&(p: Prop): Prop = new Prop {
    def check: Boolean = p.check && this.check
  }
}
*/
trait Prop {
  def check: Either[(FailedCase, SuccessCount), SuccessCount]
}

object Prop {
  type SuccessCount = Int
  type FailedCase = String
}
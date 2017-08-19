package scalaz.iomonad

import scalaz.monoids.Monad

case class Player(name: String, score: Int)

sealed trait IO[A] { self =>
  def run: A
  def map[B](f: A => B): IO[B] =
    new IO[B] { def run = f(self.run) }
  def flatMap[B](f: A => IO[B]): IO[B] = new IO[B] { def run = f(self.run).run }
}

object IO extends Monad[IO] {

  def unit[A](a: => A): IO[A] = new IO[A] { def run = a }
  def flatMap[A, B](fa: IO[A])(f: A => IO[B]) = fa flatMap f
  def apply[A](a: => A): IO[A] = unit(a)

  def winner(p1: Player, p2: Player): Option[Player] =
    if(p1.score > p2.score) Some(p1)
    else if(p1.score < p2.score) Some(p2)
    else None

  def winnerMsg(p: Option[Player]): String = p map {
    case Player(name, _) => s"$name is the winner!"
  } getOrElse "It's a draw."

  def PrintLine(msg: String): IO[Unit] = IO { println(msg) }

  /**
    * 実行する必要があるアクションを説明するだけで、それを実際に実行しない(純粋関数）
    * @param p1
    * @param p2
    * @return
    */
  def content(p1: Player, p2: Player): IO[Unit] =
    PrintLine(winnerMsg(winner(p1, p2)))

  def ReadLine: IO[String] = IO { readLine }

  def fahrenheitToCelsius(f: Double): Double = (f - 32) * 5.0/9.0

  def converter: IO[Unit] = for {
    _ <- PrintLine("Enter a temperature in degrees Fahrenheit: ")
    d <- ReadLine.map(_.toDouble)
    _ <- PrintLine("fahrenheitToCelsius(d).toString")
  } yield ()

  val readInt = ReadLine.map(_.toInt)
}


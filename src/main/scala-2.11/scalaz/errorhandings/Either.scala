package scalaz.errorhandings


sealed trait Either[+E, +A] {
  def mean(xs: IndexedSeq[Double]): Either[String, Double] =
    if(xs.isEmpty)
      Left("mean of empty list!")
    else
      Right(xs.sum / xs.length)

  def safeDiv(x: Int, y: Int): Either[Exception, Int] =
    try Right(x / y)
    catch { case e: Exception => Left(e) }

  def Try[A](a: => A): Either[Exception, A] =
    try Right(a)
    catch { case e: Exception => Left(e) }

  def map[B](f: A => B): Either[E, B] = this match {
    case Right(a) => Right(f(a))
    case Left(a) => Left(a)
  }

  def flatMap[EE >: E, B](f: A => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => f(a)
    case Left(a) => Left(a)
  }

  def orElse[EE >: E, B >: A](b: => Either[EE, B]): Either[EE, B] = this match {
    case Right(a) => Right(a)
    case Left(_) => b
  }

  def map2[EE >: E, B, C](b: Either[EE, B])(f: (A, B) => C): Either[EE, C] =
    this flatMap (aa => b map (bb => f(aa, bb)))
  /**
    for{
      aa <- this
      bb <- b
    } yield f(aa, bb)
    */

  def sequence[E, A](es: List[Either[E, A]]): Either[E, List[A]] = es match {
    case h :: t => h flatMap (hh => sequence(t) map (hh :: _))
    case Nil => Right(Nil)
  }

  def traverse[E, A, B](as: List[A])(f: A => Either[E, B]): Either[E, List[B]] = as match {
    case h :: t => (f(h) map2 traverse(t)(f))(_ :: _)
    case Nil => Right(Nil)
  }
}

case class Left[+E](value: E) extends Either[E, Nothing]
case class Right[+A](value: A) extends Either[Nothing, A]

trait Partial[+A,+B]
case class Errors[+A](get: Seq[A]) extends Partial[A,Nothing]
case class Success[+B](get: B) extends Partial[Nothing,B]


package scalaz.datastructures

sealed trait List[+A]
case object Nil extends List[Nothing]
case class Cons[+A](head: A, tail: List[A]) extends List[A]

object List {
  def sum(ints: List[Int]): Int = ints match {
    case Nil => 0
    case Cons(x, xs) => x + sum(xs)
  }

  def product(ds: List[Double]): Double = ds match {
    case Nil => 1.0
    case Cons(0.0, _) => 0.0
    case Cons(x, xs) => x * product(xs)
  }

  def tail[A](ints: List[A]): List[A] = ints match {
    case Cons(_, xs) => xs
    case Nil => Nil
  }

  def setHead[A](head: A, ints: List[A]): List[A] = ints match {
    case Cons(_, xs) => Cons(head, xs)
    case Nil => Nil
  }

  def drop[A](l: List[A], n: Int): List[A] = l match {
    case Nil => Nil
    case Cons(_, xs) => if(n > 0) drop(xs, n-1) else l
  }

  def dropWhile[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Cons(x, xs) => if(f(x)) dropWhile(xs)(f) else l
    case _ => l
  }

  def append[A](a1: List[A], a2: List[A]): List[A] = a1 match {
    case Nil => a2
    case Cons(x, xs) => Cons(x, append(xs, a2))
  }

  def init[A](l: List[A]): List[A] = l match {
    case Nil => Nil
    case Cons(x, Nil) => Nil
    case Cons(x, xs) => Cons(x, init(xs))
  }

  def foldRight[A, B](as: List[A], z: B)(f: (A, B) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => f(x, foldRight(xs, z)(f))
  }

  def foldLeft[A, B](as: List[A], z: B)(f: (B, A) => B): B = as match {
    case Nil => z
    case Cons(x, xs) => {
      println(f(z, x))
      foldLeft(xs, f(z, x))(f)
    }
  }

  def sumByFoldLeft(ints: List[Int]): Int = foldLeft(ints, 0)(_ + _)

  def productByFoldLeft(ints: List[Double]): Double = foldLeft(ints, 1.0)(_ * _)

  def lengthByFoldLeft[A](ints: List[A]): Int = foldLeft(ints, 0)((y, _) => 1 + y)

  def sum2(ints: List[Int]): Int = foldRight(ints, 0)(_ + _)

  def product2(ints: List[Double]): Double = foldRight(ints, 1.0)(_ * _)

  def length[A](l: List[A]): Int = foldRight(l, 0)((_, y) => 1 + y)

  def reverse[A](l: List[A]): List[A] =
    foldLeft(l, List[A]())((xs, x) => Cons(x, xs))

  def foldLeftByRight[A, B](as: List[A], z: B)(f: (B, A) => B): B =
    foldRight(as, (b: B) => b)((a, g) => b => g(f(b, a)))(z)

  def appendByFoldRight[A](a1: List[A], a2: List[A]): List[A] = foldRight(a1, a2)(Cons(_, _))

  def concat[A](as: List[List[A]]): List[A] = as match {
    case Cons(x: List[A], xs: List[List[A]]) => append(x, concat(xs))
    case _ => Nil: List[A]
  }

  def concatByRight[A](as: List[List[A]]): List[A] =
    foldRight(as, Nil:List[A])(append)
  def plusOne(as: List[Int]): List[Int] =
    foldRight(as, Nil: List[Int])((x, y) => Cons(x+1, y))

  def transferToString(as: List[Double]): List[String] =
    foldRight(as, Nil: List[String])((x, y) => Cons(x.toString, y))

  def map[A, B](as: List[A])(f: A => B): List[B] =
    foldRight(as, Nil: List[B])((x, y) => Cons(f(x), y))

  def filter[A](as: List[A])(f: A => Boolean): List[A] =
    foldRight(as, Nil: List[A])((x, y) => if(f(x)) Cons(x, y) else y)

  def flatMap[A, B](as: List[A])(f: A => List[B]): List[B] =
    concat(map(as)(f))

  def flatMapByRight[A, B](as: List[A])(f: A => List[B]): List[B] =
    foldRight(as, Nil: List[B])((x, y) => append(f(x), y))

  def filterByFlatMap[A](as: List[A])(f: A => Boolean): List[A] =
    flatMap(as)((a => if(f(a)) List(a) else Nil))

  def zipAdd(l1: List[Int], l2: List[Int]): List[Int] = (l1, l2) match {
    case (Cons(x1, y1), Cons(x2, y2)) => Cons(x1+x2, zipAdd(y1, y2))
    case _ => Nil
  }

  def zipWith[A, B, C](l1: List[A], l2: List[B])(f: (A, B) => C): List[C] = (l1, l2) match {
    case (Cons(x1, y1), Cons(x2, y2)) => Cons(f(x1, x2), zipWith(y1, y2)(f))
    case (Nil, _) => Nil
    case (_, Nil) => Nil
  }

  def apply[A](as: A*): List[A] =
    if(as.isEmpty) Nil
    else Cons(as.head, apply(as.tail: _*))
}

object Main extends App {
  val list1 = List(1, 2, 3, 4)
  val list2 = List(5, 6)
  println(List.flatMapByRight(List(1,2,3))(i => List(i, i)))
}
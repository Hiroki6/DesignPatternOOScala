package algorithm

object QuickSort extends App {

  def quickSort(seq: Seq[Int]): Seq[Int] = seq match {
    case x :: xs => {
      val smallerOrEqual = xs.filter(_ <= x)
      val largetOrEqual = xs.filter(_ > x)
      quickSort(smallerOrEqual) ++ Seq(x) ++ quickSort(largetOrEqual)
    }
    case Nil => Nil
  }

  println(quickSort(Seq(4, 2, 7, 1, 12, 6)))
}

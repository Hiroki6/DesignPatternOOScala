package scalaz.streamingio

import scalaz.iomonad.IO

/**
  * ドライバを使って制御しなければならない機械
  * @tparam I
  * @tparam O
  */
sealed trait Process[I, O] {
  def apply(s: Stream[I]): Stream[O] = this match {
    case Halt() => Stream()
    case Await(recv) => s match {
      case h #:: t => recv(Some(h))(t)
      case xs => recv(None)(xs)
    }
    case Emit(h, t) => h #:: t(s)
  }

  /**
    * 一つの要素を待ち受け、それを書き出して停止する処理をループで繰り返す関数
    */
  def repeat: Process[I, O] = {
    def go(p: Process[I, O]): Process[I, O] = p match {
      case Halt() => go(this)
      case Await(recv) => Await {
        case None => recv(None)
        case i => go(recv(i))
      }
      case Emit(h, t) => Emit(h, go(t))
    }
    go(this)
  }

  def filter[I](p: I => Boolean): Process[I, I] =
    Await[I, I] {
      case Some(i) if p(i) => Emit(i, Halt())
      case _ => Halt()
    }.repeat
}

// head値を出力ストリームに書き出さなければならないことをドライバに合図する
case class Emit[I, O] (
  head: O,
  tail: Process[I, O] = Halt[I, O]()) extends Process[I, O]

// 入力ストリームの値をリクエストする。ドライバは次に利用可能な値をrecv関数に渡し、それ以上要素がない場合はNoneを渡す
case class Await[I, O](recv: Option[I] => Process[I, O]) extends Process[I, O]

// それ以上入力を読み取らない、または出力に書き出さないことをドライバに合図する
case class Halt[I, O]() extends Process[I, O]

object StreamingIO extends App {

  def linesGt40k(filename: String): IO[Boolean] = IO {
    val src = io.Source.fromFile(filename)
    try {
      var count = 0
      val lines: Iterator[String] = src.getLines
      while(count <= 40000 && lines.hasNext) {
        lines.next
        count += 1
      }
      count > 40000
    }
    finally src.close
  }

  def lines(filename: String): IO[Stream[String]] = IO {
    val src = io.Source.fromFile(filename)
    src.getLines.toStream append { src.close; Stream.empty }
  }

  def liftOne[I, O](f: I => O): Process[I, O] =
    Await {
      case Some(i) => Emit(f(i), Halt())
      case None => Halt()
    }

  def lift[I, O](f: I => O): Process[I, O] = liftOne(f).repeat

  def sum: Process[Double, Double] = {
    def go(acc: Double): Process[Double, Double] =
      Await {
        case Some(d) => Emit(d+acc, go(d+acc))
        case None => Halt()
      }
    go(0.0)
  }
  val s = sum(Stream(1.0, 2.0, 3.0, 4.0)).toList
  println(s)
}


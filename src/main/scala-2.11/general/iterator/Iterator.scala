package general.iterator

trait Iterator[T] {
  def hasNext: Boolean
  def next: T
}

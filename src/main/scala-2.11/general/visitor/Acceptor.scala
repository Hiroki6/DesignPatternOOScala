package general.visitor

trait Acceptor {
  def accept(v: Visitor)
}
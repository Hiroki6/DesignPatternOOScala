package general.visitor

trait Entry extends Acceptor {
  def getName: String
  def getSize: Int
  def add(entry: Entry) = sys.error("not add")
  //def iterator: Iterator = sys.error("not add")
  override def toString = getName + "(" + getSize + ")"
}
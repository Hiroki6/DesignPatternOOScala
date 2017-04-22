package general.composite

trait Entry {
  def getName: String
  def getSize: Int
  def printList: Unit = printList("")
  def printList(prefix: String): String
  override def toString = getName + " (" + getSize + ")"
}

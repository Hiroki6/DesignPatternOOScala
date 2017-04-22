package general.visitor

class Directory(name: String) extends Entry {
  val dir = Vector()
  def getName: String = name
  def getSize: Int = ???
  def accept(v: Visitor) = ???
}

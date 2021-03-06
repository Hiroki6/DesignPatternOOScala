package general.builder

trait Builder {
  def makeTitle(title: String)
  def makeString(str: String)
  def makeItems(items: Array[String])
  def close
}
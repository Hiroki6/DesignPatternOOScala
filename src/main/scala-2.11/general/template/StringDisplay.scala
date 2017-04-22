package general.template

class StringDisplay(string: String) extends AbstractDisplay {
  val width = this.string.getBytes.length
  def open = printLine
  def printOb = println("|" + this.string + "|")
  def close = printLine
  private def printLine = {
    print("+")
    for(i <- 0 to width) {
      print("-")
    }
    println("+")
  }

}

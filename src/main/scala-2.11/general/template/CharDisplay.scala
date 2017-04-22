package general.template

class CharDisplay(ch: Char) extends AbstractDisplay {
  def open = print("<<")
  def printOb = print(this.ch)
  def close = println(">>")
}

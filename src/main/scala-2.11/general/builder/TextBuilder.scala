package general.builder

class TextBuilder(buffer: StringBuilder) extends Builder {

  def makeTitle(title: String) = {
    this.buffer.append("=========================¥n")
    this.buffer.append(" [" + title + "]¥n")
    this.buffer.append("¥n")
  }

  def makeString(str: String) = {
    this.buffer.append('■' + str + "¥n")
    this.buffer.append("¥n")
  }

  def makeItems(items: Array[String]) = {
    for(i <- 0 to items.length) {
      this.buffer.append("  ・" + items(i) + "¥n")
    }
    this.buffer.append("¥n")
  }

  def close: Unit = {
    this.buffer.append("=========================¥n")
  }

  def getResult: String = {
    this.buffer.toString
  }

}
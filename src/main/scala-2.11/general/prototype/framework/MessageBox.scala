package general.prototype

import general.prototype.framework._

class MessageBox(decochar: Char) extends Product {

  def use(s: String): Unit = {
    val length = s.getBytes.length
    for(i <- 0 to length + 4) {
      print(decochar)
    }
    println()
    print(decochar + " " + s + " " + decochar)
    for(i <- 0 to length + 4) {
      print(decochar)
    }
    println()
  }

  def createClone: Product = {
    val p: Product = null
    p
  }
}
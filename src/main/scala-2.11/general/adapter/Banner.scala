package general.adapter

class Banner(string: String) {

  def showWithParen: Unit = println("(" + this.string + ")")

  def showWithAster: Unit = println("*" + this.string + "*")

}
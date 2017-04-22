package general.template

object Main extends App {
  val d1: AbstractDisplay = new CharDisplay('H')
  val d2: AbstractDisplay = new StringDisplay("Hello, World.")
  val d3: AbstractDisplay = new StringDisplay("こんにちわ。")
  d1.display
  d2.display
  d3.display
}



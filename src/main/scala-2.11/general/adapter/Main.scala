package general.adapter

object Main extends App {
  val p: Print = new PrintBanner("Hello")
  p.printWeak
  p.printStrong
}

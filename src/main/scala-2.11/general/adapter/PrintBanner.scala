package general.adapter

class PrintBanner(string: String) extends Banner(string) with Print {
  def printWeak: Unit = showWithParen
  def printStrong: Unit = showWithAster
}

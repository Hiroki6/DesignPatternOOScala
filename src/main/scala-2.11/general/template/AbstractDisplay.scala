package general.template

trait AbstractDisplay {
  def open
  def printOb
  def close
  final def display: Unit = {
    open
    for(i <- 0 to 5) {
      printOb
    }
    close
  }
}

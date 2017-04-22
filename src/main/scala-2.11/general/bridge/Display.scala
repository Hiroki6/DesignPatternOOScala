package general.bridge

class Display(impl: DisplayImpl) {
  def open = impl.rawOpen
  def printExec = impl.rawPrint
  def close = impl.rawClose
  final def display = {
    open
    printExec
    close
  }
}

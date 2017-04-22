package general.bridge

class CountDisplay(impl: DisplayImpl) extends Display(impl) {

  def multiDisplay(times: Int): Unit = {
    open
    for(i <- 0 to times) {
      printExec
    }
    close
  }

}
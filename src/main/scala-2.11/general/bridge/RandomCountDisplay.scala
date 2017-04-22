package general.bridge

import scala.util.Random

class RandomCountDisplay(impl: DisplayImpl) extends Display(impl) {

  def randomDisplay = {
    val randomInt = Random.nextInt(30)
    open
    for(i <- 0 to randomInt) {
      printExec
    }
    close
  }
}
package general.strategy

import scala.util.Random

class WinningStrategy(seed: Int, var prevHand: Hand) extends Strategy {

  val random: Random = new Random(seed)
  var won: Boolean = false

  def nextHand: Hand = {
    if(!won) {
      prevHand = Hand.getHand(random.nextInt(3))
    }
    prevHand
  }

  def study(win: Boolean) = {
    won = win
  }
}

package general.strategy

import scala.util.Random

class ProbStrategy(var prevHandValue: Int, var currentHandValue: Int, seed: Int) extends Strategy {

  val random = new Random(seed)
  val history: Array[Array[Int]] = Array(Array(1,1,1),Array(1,1,1),Array(1,1,1))

  def nextHand: Hand = {
    val bet = random.nextInt(getSum(currentHandValue))
    val handValue = getHandValue(bet)
    prevHandValue = currentHandValue
    currentHandValue = handValue
    Hand.getHand(handValue)
  }

  def getHandValue(bet: Int): Int =
    if(bet < history(currentHandValue)(0)) 0
    else if(bet < history(currentHandValue)(0) + history(currentHandValue)(1)) 1
    else 2


  def getSum(hv: Int): Int = history(hv).sum

  def study(win: Boolean): Unit = {
    if(win) {
      history(prevHandValue)(currentHandValue) += 1
    } else {
      history(prevHandValue)((currentHandValue + 1) % 3) += 1
      history(prevHandValue)((currentHandValue + 2) % 3) += 1
    }
  }

}
package general.strategy

trait Strategy {
  def nextHand: Hand
  def study(win: Boolean): Unit
}
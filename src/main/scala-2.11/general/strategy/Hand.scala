package general.strategy

case class Hand(handValue: Int)

object Hand {

  val handValue: Int = handValue
  val HANDVALUE_GUU = 0
  val HANDVALUE_CHO = 1
  val HANDVALUE_PAA = 2
  val hand: Array[Hand] = Array(Hand(HANDVALUE_GUU), Hand(HANDVALUE_CHO), Hand(HANDVALUE_PAA))

  val name: Array[String] = Array("グー", "チョキ", "パー")

  def getHand(handValue: Int) = {
    hand(handValue)
  }

  // thisがhより強い時true
  def isStrongerThan(h: Hand): Boolean = fight(h) == 1

  // thisがhより弱い時true
  def isWeakerThan(h: Hand): Boolean = fight(h) == -1

  def fight(h: Hand): Int = {
    if(this == h) {
      0
    } else if((this.handValue + 1) % 3 == h.handValue) {
      1
    } else {
      -1
    }
  }

  override def toString: String = name(handValue)

}

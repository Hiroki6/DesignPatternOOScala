package general.strategy

class Player(name: String, strategy: Strategy) {
  var wincount = 0
  var losecount = 0
  var gamecount = 0

  def win: Unit = {
    strategy.study(true)
    wincount += 1
    gamecount += 1
  }

  def lose: Unit = {
    strategy.study(false)
    losecount += 1
    gamecount += 1
  }

  def even: Unit = {
    gamecount += 1
  }

  override def toString: String = {
    "[" + name + ":" + gamecount + " games," + wincount + " win, " + losecount + " lose" + "]"
  }

}
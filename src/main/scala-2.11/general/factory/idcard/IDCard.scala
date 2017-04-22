package general.factory.idcard
import general.factory.framework._

class IDCard(owner: String, serial: Int) extends Product {
  println(this.owner + "(" + serial + ")のカードを作ります")
  def use = println(owner + "(" + serial + ")のカードを使います")
  def getOwner: String = this.owner
  def getSerial: Int = this.serial
}

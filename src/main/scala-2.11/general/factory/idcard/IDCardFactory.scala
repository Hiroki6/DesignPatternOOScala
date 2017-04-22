package general.factory.idcard
import general.factory.framework._
import scala.collection.immutable.Vector
import scala.collection.Map

class IDCardFactory(var database: Map[String, Int] = Map[String, Int](), var serial: Int = 100) extends Factory[IDCard] {

  def createProduct(owner: String) = new IDCard(owner, serial = serial + 1)

  def registerProduct(product: IDCard) = {
    val card = product
    database.updated(card.getOwner, card.getSerial)
  }
}

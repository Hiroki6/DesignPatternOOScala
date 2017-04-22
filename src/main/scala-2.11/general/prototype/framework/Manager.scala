package general.prototype.framework

class Manager(showcase: Map[String, Product] = Map[String, Product]()) {

  def register(name: String, proto: Product): Unit = {
    showcase.updated(name, proto)
  }

  def create(protoname: String): Unit = {
    val p: Option[Product] = showcase.get(protoname)
    p.map(_.createClone)
  }
}
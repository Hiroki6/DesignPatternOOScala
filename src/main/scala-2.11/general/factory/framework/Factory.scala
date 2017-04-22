package general.factory.framework

trait Factory[T <: Product] {
  final def create(owner: String): T = {
    val p: T = createProduct(owner)
    registerProduct(p)
    p
  }

  def createProduct(owner: String): T
  def registerProduct(product: T): Unit
}
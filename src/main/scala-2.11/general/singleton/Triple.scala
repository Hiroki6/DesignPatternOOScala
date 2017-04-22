package general.singleton

/**
  * コンパニオンオブジェクトを用いてインスタンスの個数が３個に制限されているTripleクラスを実現
  * @param id
  */
case class Triple(id: Int) {
  println("The instance " + id + " is created.")
  override def toString = "[Triple id=" + id + "]"
}

object Triple {
  private val triple: Array[Triple] = Array(Triple(0), Triple(1), Triple(2))

  def getInstance(id: Int) = triple(id)
}
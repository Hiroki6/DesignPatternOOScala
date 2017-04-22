package general.singleton

object Main extends App {
  for(i <- 0 to 9) {
    val triple = Triple.getInstance(i % 3)
    println(i + ":" + triple)
  }
  println("End.")
}

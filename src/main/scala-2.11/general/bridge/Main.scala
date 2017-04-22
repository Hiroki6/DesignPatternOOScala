package general.bridge

object Main extends App {
  val d1 = new Display(new StringDisplayImpl("Hello, Japan"))
  val d2 = new CountDisplay(new StringDisplayImpl("Hello, World."))
  val d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe."))
  val d4 = new RandomCountDisplay(new StringDisplayImpl("Hello, Space."))
  d1.display
  d2.display
  d3.display
  d3.multiDisplay(5)
  d4.randomDisplay
}
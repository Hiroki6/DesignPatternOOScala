package peculiar.unification.adts

sealed abstract trait Month
case object January extends Month
case object February extends Month
case object March extends Month
case object April extends Month
case object May extends Month
case object June extends Month
case object July extends Month
case object August extends Month
case object September extends Month
case object October extends Month
case object November extends Month
case object December extends Month

object MonthDemo {
  def main(args: Array[String]): Unit = {
    val month: Month = February
    System.out.println(s"The current month is: $month")
  }
}

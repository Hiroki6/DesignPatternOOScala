package peculiar.traits.mixin

abstract class Connector {
  def connect()
  def close()
}

trait ConnectorWithHelper extends Connector {
  def findDriver(): Unit = {
    System.out.println("Find driver called.")
  }
}

object ReallyExpensiveWatchUser {
  def main(args: Array[String]): Unit = {
    /*val reallyExpensiveWatchUser = new Watch("really expensive brand",
      1000L) with ConnectorWithHelper {
      override def connect(): Unit = {
        System.out.println("Connected with another connector")
      }
      override def close(): Unit = {
        System.out.println("Closed with another connector.")
      }
    }
    System.out.println("Using the really expensive watch.")
    reallyExpensiveWatchUser.findDriver()
    reallyExpensiveWatchUser.connect()
    reallyExpensiveWatchUser.close()*/
  }
}
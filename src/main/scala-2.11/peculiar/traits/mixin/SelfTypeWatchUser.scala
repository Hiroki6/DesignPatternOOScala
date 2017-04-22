package peculiar.traits.mixin

object SelfTypeWatchUser {
  def main(args: Array[String]): Unit = {
    val watch = new Watch("alarm with notification", 1000L) with AlarmNotifier with Notifier {
      def trigger(): String = "Alarm triggered."

      def clear(): Unit = {
        System.out.println("Alarm cleared.")
      }

      val notificationMessage: String = "The notification."
    }

    System.out.println(watch.trigger())
    watch.printNotification()
    System.out.println(s"The time is ${watch.getTime()}.")
    watch.clear()
  }
}
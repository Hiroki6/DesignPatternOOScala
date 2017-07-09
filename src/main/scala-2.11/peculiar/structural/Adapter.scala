package peculiar.structural

class Logger {
  def log(message: String, severity: String): Unit = {
    System.out.println(s"${severity.toUpperCase}: $message")
  }
}

class FinalLogger extends Logger

trait Log {
  def info(message: String)
  def debug(message: String)
  def warning(message: String)
  def error(message: String)
}

class AppLogger extends Logger with Log {
  def info(message: String): Unit = log(message, "info")

  def warning(message: String): Unit = log(message, "warning")

  def error(message: String): Unit = log(message, "error")

  def debug(message: String): Unit = log(message, "debug")
}

class FinalAppLogger extends Log {
  private val logger = new FinalLogger

  def info(message: String): Unit = logger.log(message, "info")

  def warning(message: String): Unit = logger.log(message, "warning")

  def error(message: String): Unit = logger.log(message, "error")

  def debug(message: String): Unit = logger.log(message, "debug")
}

object Adapter {
  def main(args: Array[String]): Unit = {
    val logger = new AppLogger
    logger.info("This is an info message.")
    logger.debug("Debug something here.")
    logger.warning("Show an error message.")
    logger.info("Bye!")
  }
}
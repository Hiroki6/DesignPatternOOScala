package peculiar

/**
  * Created by fujinohiroki on 2017/04/30.
  */
package object structural {


  implicit class FinalAppLoggerImplicit(logger: FinalLogger) extends Log {
    def info(message: String): Unit = logger.log(message, "info")

    def warning(message: String): Unit = logger.log(message,  "warning")

    def error(message: String): Unit = logger.log(message, "error")

    def debug(message: String): Unit = logger.log(message, "debug")
  }
}

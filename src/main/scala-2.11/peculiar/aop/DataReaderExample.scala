package peculiar.aop

import org.json4s.{DefaultFormats, StreamInput}
import org.json4s.jackson.JsonMethods.parse

case class Person(firstName: String, lastName: String, age: Int)

trait DataReader {
  def readData(): List[Person]
  def readDataInefficiently(): List[Person]
}

class DataReaderImpl extends DataReader {
  implicit val formats = DefaultFormats
  private def readUntimed(): List[Person] =
    parse(StreamInput(getClass.getResourceAsStream("users.json"))).extract[List[Person]]

  override def readData(): List[Person] =
    readUntimed()

  override def readDataInefficiently(): List[Person] = {
    (1 to 10000).foreach {
      case num =>
        readUntimed()
    }
    readUntimed()
  }
}

trait LoggingDataReader extends DataReader {

  abstract override def readData(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readData()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readData took ${time} milliseconds.")
    result
  }

  abstract override def readDataInefficiently(): List[Person] = {
    val startMillis = System.currentTimeMillis()
    val result = super.readDataInefficiently()
    val time = System.currentTimeMillis() - startMillis
    System.err.println(s"readDataInefficiently took ${time} milliseconds.")
    result
  }
}

object DataReaderAOPExample {
  def main(args: Array[String]): Unit = {
    val dataReader = new DataReaderImpl with LoggingDataReader
    System.out.println(s"I just read the following data efficiently: ${dataReader.readData()}")
    System.out.println(s"I just read the following data inefficiently: ${dataReader.readDataInefficiently()}")
  }
}

object DataReaderExample {
  def main(args: Array[String]): Unit = {
    val dataReader = new DataReaderImpl
    System.out.println(s"I just read the following data efficiently: ${dataReader.readData()}")
    System.out.println(s"I just read the following data inefficiently: ${dataReader.readDataInefficiently()}")
  }
}
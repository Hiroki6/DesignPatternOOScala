package peculiar.creational

trait SimpleConnection {
  def getName(): String
  def executeQuery(query: String): Unit
}

trait SimpleConnectionPrinter {
  def printSimpleConnection(conneciton: SimpleConnection): Unit
}

class SimpleMysqlConneciton extends SimpleConnection {
  def getName(): String = "SimpleMysqlConnetion"

  def executeQuery(query: String): Unit = {
    System.out.println(s"Executing the query '$query' the MySQL way.")
  }
}

class SimplePgSqlConnection extends SimpleConnection {
  def getName(): String = "SimplePqSqlConnetion"

  def executeQuery(query: String): Unit = {
    System.out.println(s"Executing the query '$query' the PgSQL way.")
  }
}

trait DatabaseClient {
  def executeQuery(query: String): Unit = {
    val connection = connect()
    connection.executeQuery(query)
  }

  protected def connect(): SimpleConnection
}

trait BadDatabaseClient {
  def executeQuery(query: String): Unit = {
    val conneciton = connect()
    val connectionPrinter = getConnectionPrinter()
    connectionPrinter.printSimpleConnection(conneciton)
    conneciton.executeQuery(query)
  }

  protected def connect(): SimpleConnection
  protected def getConnectionPrinter(): SimpleConnectionPrinter
}

class MysqlClient extends DatabaseClient {
  protected def connect(): SimpleConnection = new SimpleMysqlConneciton
}

class PdSqlClient extends DatabaseClient {
  protected def connect(): SimpleConnection = new SimplePgSqlConnection
}

class SimpleMySqlConnectionPrinter extends SimpleConnectionPrinter {
 def printSimpleConnection(connection: SimpleConnection): Unit = {
   System.out.println(s"I require a MySQL connection. It is: '${connection.getName()}")
 }
}

class SimplePgSQLConnectionPrinter extends SimpleConnectionPrinter {
  def printSimpleConnection(connection: SimpleConnection): Unit = {
    System.out.println(s"I require a PgSQL connection. It is: '${connection.getName()}")
  }
}

class BadMySqlClient extends BadDatabaseClient {
  protected def connect(): SimpleConnection = new SimpleMysqlConneciton

  protected def getConnectionPrinter(): SimpleConnectionPrinter = new SimpleMySqlConnectionPrinter
}

class BadPgSqlClient extends BadDatabaseClient {
  protected def connect(): SimpleConnection = new SimplePgSqlConnection

  protected def getConnectionPrinter(): SimpleConnectionPrinter = new SimplePgSQLConnectionPrinter
}

object DatabaseExample {
  def main(args: Array[String]): Unit = {
    val clientMySql: BadDatabaseClient = new BadMySqlClient
    val clientPgSql: BadDatabaseClient = new BadPgSqlClient
    clientMySql.executeQuery("SELECT * FROM users")
    clientPgSql.executeQuery("SELECT * FROM employees")
  }
}
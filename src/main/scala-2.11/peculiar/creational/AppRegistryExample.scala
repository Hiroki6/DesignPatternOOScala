package peculiar.creational

import scala.collection.mutable.Map

object AppRegistry {
  System.out.println("Registry initialization block called.")
  private val users: Map[String, String] = Map[String, String]()

  def addUser(id: String, name: String): Unit = {
    users.put(id, name)
  }

  def removeUser(id: String): Unit = {
    users.remove(id)
  }

  def isUserRegistry(id: String): Boolean = users.contains(id)

  def getAllUserNames(): List[String] = users.map(_._2).toList
}

object AppRegistryExample {
  def main(args: Array[String]): Unit = {
    System.out.println("Sleeping for 5 seconds.")
    Thread.sleep(5000)
    System.out.println("I woke up.")
    AppRegistry.addUser("1",   "Ivan")
    AppRegistry.addUser("2", "John")
    AppRegistry.addUser("3", "Martin")
    System.out.println(s"Is user with ID=1 registered? ${AppRegistry.isUserRegistry("1")}")
    System.out.println("Removing ID=2")
    AppRegistry.removeUser("2")
    System.out.println(s"Is user with ID=2 registered? ${AppRegistry.isUserRegistry("2")}")
    System.out.println(s"All users registered are: ${AppRegistry.getAllUserNames().mkString(",")}")
  }
}
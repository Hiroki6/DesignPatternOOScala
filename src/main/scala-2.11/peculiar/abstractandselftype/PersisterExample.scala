package peculiar.abstractandselftype

import scala.collection.mutable

trait Persister[T] {
  this: Database[T] with History with Mystery =>
    def persist(data: T): Unit = {
      System.out.println("Calling persist.")
      save(data)
      add()
    }
}

/*
trait Persister[T] extends Database[T] with History {
  def persist(data: T): Unit = {
    System.out.println("Calling persist.")
    save(data)
    add()
  }
}
*/

trait Database[T] {
  def save(data: T)
}

trait MemoryDatabase[T] extends Database[T] {
  val db: mutable.MutableList[T] = mutable.MutableList.empty
  override def save(data: T): Unit = {
    System.out.println("Saving to in memory database.")
    db. +=: (data)
  }
}

trait FileDatabase[T] extends Database[T] {
  override def save(data: T): Unit = {
    System.out.println("Saving to file.")
  }
}

trait History {
  def add(): Unit = {
    System.out.println("Action added to history.")
  }
}

trait Mystery {
  def add(): Unit = {
    System.out.println("Mystery added!")
  }
}

// Historyを持っていないため
class FilePersister[T] extends Persister[T] with FileDatabase[T] with History with Mystery {
  override def add(): Unit = {
    super[History].add()
  }
}

class MemoryPersisiter[T] extends Persister[T] with MemoryDatabase[T] with History with Mystery {
  override def add(): Unit = {
    super[Mystery].add()
  }
}

object PersisterExample {
  def main(args: Array[String]): Unit = {
    val fileStringPersister = new FilePersister[String]
    val memoryIntPersister = new MemoryPersisiter[Int]

    fileStringPersister.persist("Something")
    fileStringPersister.persist("Something else")

    memoryIntPersister.persist(100)
    memoryIntPersister.persist(123)
  }
}
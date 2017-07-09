package peculiar.creational

/*class Person(builder: PersonBuilder) {
  val firstName = builder.firstName
  val lastName = builder.lastName
  val age = builder.age
}

class PersonBuilder {
  var firstName = ""
  var lastName = ""
  var age = 0
  def setFirstName(firstName: String): PersonBuilder = {
    this.firstName = firstName
    this
  }

  def setLastName(lastName: String): PersonBuilder = {
    this.lastName = lastName
    this
  }

  def setAge(age: Int): PersonBuilder = {
    this.age = age
    this
  }

  def build(): Person = new Person(this)
}

object PersonBuilderExample {
  def main(args: Array[String]): Unit = {
    val person: Person = new PersonBuilder()
      .setFirstName("Ivan")
      .setLastName("Nikolov")
      .setAge(26)
      .build()
    System.out.println(s"Person: ${person.firstName} ${person.lastName}. Age: ${person.age}")
  }
}*/
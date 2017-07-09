package lens

case class Country(name: String, code: String)
case class City(name: String, country: Country)
case class Address(number: Int, street: String, city: City)
case class Company(name: String, address: Address)
case class User(name: String, company: Company, address: Address)
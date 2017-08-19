package peculiar.lens

import lens._

import scalaz.Lens

//case class Lens[X, Y](get: X => Y, set: (X, Y) => X)

object User {
  val userCompany = Lens.lensu[User, Company](
    (u, company) => u.copy(company = company),
    _.company
  )

  val userAddress = Lens.lensu[User, Address](
    (u, address) => u.copy(address = address),
    _.address
  )

  val companyAddress = Lens.lensu[Company, Address](
    (c, address) => c.copy(address = address),
    _.address
  )

  val addressCity = Lens.lensu[Address, City](
    (a, city) => a.copy(city = city),
    _.city
  )

  val cityCountry = Lens.lensu[City, Country](
    (c, country) => c.copy(country = country),
    _.country
  )

  val countryCode = Lens.lensu[Country, String](
    (c, code) => c.copy(code = code),
    _.code
  )

  val userCompanyCountryCode = userCompany >=> companyAddress >=> addressCity >=> cityCountry >=> countryCode

}
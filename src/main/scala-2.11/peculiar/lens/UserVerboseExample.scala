package lens

object UserVerboseExample {
  def main(args: Array[String]): Unit = {
    val uk = Country("United Kingdom", "uk")
    val london = City("London", uk)
    val buckinghamPalace = Address(1, "Buckingham Palace Road", london)
    val castleBuilders = Company("Castle Builders", buckinghamPalace)
    val switzerland = Country("Switzerland", "CH")
    val geneva = City("geneva", switzerland)
    val genevaAddress = Address(1, "Geneva Lake", geneva)

    val ivan = User("Ivan", castleBuilders, genevaAddress)

    System.out.println("Capitalize UK code...")

    // immutableなので一部変更するのに手間がかかる
    // 間違えが起きやすく、保守性が低い
    val ivanFixed = ivan.copy(
      company = ivan.company.copy(
        address = ivan.company.address.copy(
          city = ivan.company.address.city.copy(
            country = ivan.company.address.city.country.copy(
              code = ivan.company.address.city.country.code.toUpperCase
            )
          )
        )
      )
    )
    System.out.println(ivanFixed)
  }
}
package peculiar.injection.cake

trait DatabaseService {
  val dbDriver: String
  val connectionString: String
  val username: String
  val password: String

}

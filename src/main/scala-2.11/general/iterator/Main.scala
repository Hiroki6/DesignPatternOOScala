package general.iterator

object Main extends App {

  val bookShelf = new BookShelf(4)
  bookShelf.appendBook(Book("Around the World in 80 Days"))
  bookShelf.appendBook(Book("Bible"))
  bookShelf.appendBook(Book("Cinderella"))
  bookShelf.appendBook(Book("Daddy-Long-Legs"))
  var it: BookShelfIterator = bookShelf.iterator
  while(it.hasNext) {
    val book = it.next
    println("" + book.getName)
  }
}

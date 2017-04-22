package general.iterator

class BookShelf(maxsize: Int) extends Aggregate[BookShelfIterator] {
  val books: Array[Book] = new Array[Book](maxsize)
  var last: Int = 0

  def getBookAt(index: Int): Book = this.books(index)

  def appendBook(book: Book) = {
    this.books(last) = book
    last += 1
  }

  def getLength: Int = last

  def iterator: BookShelfIterator = BookShelfIterator(this)
}

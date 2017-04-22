package general.iterator

case class BookShelfIterator(bookShelf: BookShelf, var index: Int = 0) extends Iterator[Book] {

  def hasNext: Boolean = this.index < bookShelf.getLength

  def next: Book = {
    val book: Book = bookShelf.getBookAt(this.index)
    this.index = this.index + 1
    book
  }
}

package general.builder

import java.io.{FileWriter, IOException, PrintWriter}

class HTMLBuilder(var filename: String = "", var writer: PrintWriter) extends Builder {

  def makeTitle(title: String) = {
    this.filename = title + ".html"
    this.writer = new PrintWriter(new FileWriter(this.filename))
    this.writer.println("<html><head><title>" + title + "</title></head><body>")
    this.writer.println("<h1>" + title + "</h1>")
  }

  def makeString(str: String): Unit = {
    this.writer.println("<p>" + str + "</p>")
  }

  def makeItems(items: Array[String]) = {
    this.writer.println("<ul>")
    for(i <- 0 to items.length) {
      this.writer.println("<li>" + items(i) + "</li>")
    }
    this.writer.println("</ul>")
  }

  def close: Unit = {
    this.writer.println("</body></html>")
    this.writer.close
  }

  def getResult: String = {
    this.filename
  }
}
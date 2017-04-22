package general.hierarchy.factory

abstract class Tray(caption: String, var tray: Vector[Item]) extends Item(caption) {

  def add(item: Item) = {
    this.tray = this.tray :+ item
  }
}
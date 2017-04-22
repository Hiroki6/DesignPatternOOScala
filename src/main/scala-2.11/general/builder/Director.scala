package general.builder

class Director[T <: Builder](builder: T) {
  def construct = {
    this.builder.makeTitle("Greeting")
    this.builder.makeString("朝から昼にかけて")
    this.builder.makeItems(Array("おはようございます", "こんにちわ。"))
    this.builder.makeString("夜に")
    this.builder.makeItems(Array("こんばんは", "おやすみなさい", "さようなら"))
    this.builder.close
  }
}
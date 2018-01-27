object ScalaIntro {
  // This is a method
  def hello(): String = {
    "hello"
  }
  // Short version
  def hello2() = "hello"

  // This is a function
  val function = (i: Int) => ???

  // Type inference
  val inferred: Int => String =
    i => i.toString

  class Person {
    def say(something: () => String): Unit = {
      println(something())
    }
  }

  val p = new Person()
  p.say(hello)
  // If there is only 1 parameter
  p.say { hello }
  p say hello

  // Multiple parameter lists
  def decorate(s: String)(how: String => String) = ???
  // Use it like this
  decorate(hello())(s => s.toUpperCase())
  // Or shorter and prettier
  decorate(hello()) { s =>
    s.toLowerCase()
  }

  // Generic
  List[String]() // in Java: List<String>
}

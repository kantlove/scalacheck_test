import org.scalacheck.Prop.forAll
import org.scalacheck.{Properties, Shrink, Gen}

object StringTest extends Properties("String") {

  property("startsWith") = forAll { (a: String, b: String) =>
    (a + b).startsWith(a)
  }

  property("concatenate") = forAll { (a: String, b: String) =>
    (a + b).length > a.length && (a + b).length > b.length
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a + b + c).substring(a.length, a.length + b.length) == b
  }

  val personGen: Gen[Person] = {
    for {
      name <- Gen.alphaLowerStr
      age <- Gen.choose(1, 100)
    } yield Person(name, age)
  }

  /**
    * If there is not enough test cases satisfied our condition, ScalaCheck will mark testing property as "undecided".
    * So when is "not enough"? ScalaCheck will discard a newly generated test case if it doesn't satisfy. If there are
    * more than 500 (be default) discarded cases, it is "not enough".
    * (this can be verified by checking the Test.scala file in the library)
    */
  val teenGen: Gen[Person] = personGen suchThat Person.isTeenager

  property("young person") = forAll(teenGen) { (p: Person) =>
    Person.isTeenager(p)
  }
}

object Person {
  def isTeenager(p: Person): Boolean = {
    p.age <= 18
  }
}

case class Person(name: String, age: Int)

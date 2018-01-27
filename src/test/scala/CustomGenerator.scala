import org.scalacheck.Prop.{collect, forAll}
import org.scalacheck.{Gen, Properties}

object NewFromExisting extends Properties("NewFromExisting") {
  val leftInt: Gen[Int] = Gen.choose(-100, 0)
  val rightInt: Gen[Int] = Gen.choose(1, 100)

  val twoInts: Gen[(Int, Int)] = leftInt.flatMap((left: Int) => {
    rightInt.map((right: Int) => {
      (left, right)
    })
  })

  val twoInts2: Gen[(Int, Int)] = leftInt.flatMap(left => {
    rightInt.map(right => {
      (left, right)
    })
  })

  val twoInts3: Gen[(Int, Int)] = leftInt flatMap { left =>
    rightInt map { right =>
      (left, right)
    }
  }

  val twoInts4: Gen[(Int, Int)] = for {
    left <- leftInt
    right <- rightInt
  } yield (left, right)

  val int: Gen[Int] = Gen.choose(1, 100)
  val intToString: Gen[String] = int.map(i => i.toString)

  val start: Gen[Int] = Gen.choose(1, 100)
  val bigger: Gen[Int] = start.flatMap(i => Gen.choose(i, 9999))

  val abc = Gen.oneOf('A', 'B', 'C')

  val sea = Gen.frequency(
    (1, 'G'),
    (2, 'A'),
    (1, 'R'),
    (1, 'E'),
    (1, 'N')
  )

  property("abc") = forAll(abc) { char =>
    collect(char) {
      Seq('A', 'B', 'C') contains char
    }
  }

  property("company") = forAll(sea) { char =>
    collect(char) {
      Seq('G', 'A', 'R', 'E', 'N') contains char
    }
  }
}

object CustomClass extends Properties("CustomClass") {

  object Person {
    def isTeenager(p: Person): Boolean = {
      p.age <= 18
    }
  }

  class Person(val name: String, val age: Int)

  val personGen: Gen[Person] = {
    for {
      name <- Gen.alphaLowerStr
      age <- Gen.choose(1, 100)
    } yield new Person(name, age)
  }

  /**
    * If there is not enough test cases satisfied our condition, ScalaCheck will mark testing property as "undecided".
    * So when is "not enough"? ScalaCheck will discard a newly generated test case if it doesn't satisfy. If there are
    * more than 500 (be default) discarded cases, it is "not enough".
    * (this can be verified by checking the Test.scala file in the library)
    */
  val teenGen: Gen[Person] = personGen suchThat Person.isTeenager

  property("teenager") = forAll(teenGen) { (p: Person) =>
    Person.isTeenager(p)
  }

}

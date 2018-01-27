import org.scalacheck.Prop.{collect, forAll}
import org.scalacheck.{Gen, Properties}

object AdditionTest extends Properties("Addition") {
  property("identity") = forAll(Gen.choose(-100, 100)) { (a: Int) =>
    collect(a) {
      add(a, 0) == a
    }
  }

  property("commutative") = forAll { (a: Int, b: Int) =>
    add(a, b) == add(b, a)
  }

  property("associative") = forAll { (a: Int, b: Int, c: Int) =>
    add(add(a, b), c) == add(a, add(b, c))
  }

  def add(a: Int, b: Int) = a + b
}

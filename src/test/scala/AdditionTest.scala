import org.scalacheck.Prop.{collect, forAll}
import org.scalacheck.rng.Seed
import org.scalacheck.{Arbitrary, Gen, Properties, Shrink}

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

  (1 to 5) foreach { _ =>
    println(Arbitrary.arbBool.arbitrary.apply(Gen.Parameters.default, Seed.random()))
  }

  def add(a: Int, b: Int) = a + b
}

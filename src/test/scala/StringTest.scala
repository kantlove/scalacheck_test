import org.scalacheck.Prop.{collect, forAll}
import org.scalacheck.{Arbitrary, Gen, Properties, Shrink}

object StringTest extends Properties("String") {

  property("reverse") = forAll(Gen.alphaNumStr) { s: String =>
    collect(s) {
      s.reverse.reverse == s
    }
  }

  property("startsWith") = forAll { (a: String, b: String) =>
    (a + b).startsWith(a)
  }

  property("concatenate") = forAll { (a: String, b: String) =>
    (a + b).length > a.length && (a + b).length > b.length
  }

  property("substring") = forAll { (a: String, b: String, c: String) =>
    (a + b + c).substring(a.length, a.length + b.length) == b
  }
}

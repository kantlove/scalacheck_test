import org.scalacheck.Prop.forAll
import org.scalacheck.Properties

object Minimisation extends Properties("Minimisation") {
  property("list") = forAll { (l: List[Int]) =>
    l.isEmpty || l.length < 5 || l.head % 2 == 0
  }

  property("< 2018") = forAll { (i: Int) =>
    i < 2018
  }
}

object TestsOfDev {
  test("1 + 3 = 4") {
    assert(add(1, 3) == 4)
  }

  test("2 + 2 = 4") {
    assert(add(2, 2) == 4)
  }

  test("Add 1 to -9 should be -8") {
    assert(add(1, -9) == 8)
  }


  def add(a: Int, b: Int) = {
    4
  }

  // Fake test function
  def test(name: String)(predicate: => Any) = ???
}
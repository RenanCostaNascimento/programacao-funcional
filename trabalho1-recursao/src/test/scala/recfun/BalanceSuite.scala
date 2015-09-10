package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class BalanceSuite extends FunSuite {

  import Main.balance

  test("balance: '(if (zero? x) max (/ 1 x))' is balanced") {
    assert(balance("(if (zero? x) max (/ 1 x))".toList))
  }
  test("balance: '(if (zero? x) max (/ 1 x)))' is not balanced") {
    assert(!balance("(if (zero? x) max (/ 1 x)))".toList))
  }
  test("balance: '(if (zero? x) max (/ 1 x))(' still not balanced") {
    assert(!balance("(if (zero? x) max (/ 1 x))(".toList))
  }
  test("balance: '' should throw NSuchElementException)") {
    intercept[NoSuchElementException]{
      balance("".toList)
    }
  }


}

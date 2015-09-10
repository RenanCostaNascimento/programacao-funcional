package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class PascalSuite extends FunSuite {

  import Main.pascal

  test("pascal: col=0,row=2") {
    assert(pascal(0,0) === 1)
  }

  // ==================================================================
  // Escreva mais testes.
  // ==================================================================

}

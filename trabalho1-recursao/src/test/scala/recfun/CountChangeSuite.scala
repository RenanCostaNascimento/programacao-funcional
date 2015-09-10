package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {

  import Main.countChange

  test("countChange: exemplo dado nas instruções") {
    assert(countChange(4,List(1,2)) === 3)
  }

  // ==================================================================
  // Escreva mais testes.
  // ==================================================================

}

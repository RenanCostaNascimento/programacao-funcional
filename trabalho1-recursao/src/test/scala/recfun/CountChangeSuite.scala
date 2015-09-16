package recfun

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Aluno: Renan Costa Nascimento
 */
@RunWith(classOf[JUnitRunner])
class CountChangeSuite extends FunSuite {

  import Main.countChange

  test("countChange: exemplo dado nas instruções.") {
    val expected = 3
    val actual = countChange(4,List(1,2))
    assert(actual === expected)
  }
  test("countChange: a ordem das moedas não deve influenciar nas quantidade de formas de dar o troco.") {
    val expected = 3
    val actual = countChange(4,List(2,1))
    assert(actual === expected)
  }
  test("countChange: quando o troco é zero, espera-se zero formas de dar troco.") {
    val expected = 0
    val actual = countChange(0,List(1,2))
    assert(actual === expected)
  }
  test("countChange: quando não há moedas para dar o troco, espera-se que o troco seja dado em balinhas (zero formas) =D.") {
    val expected = 0
    val actual = countChange(5,List())
    assert(actual === expected)
  }
  test("countChange: questão da OBM 2011") {
    val expected = 24
    val actual = countChange(37,List(1,5,10,25))
    assert(actual === expected)
  }
  test("countChange: questão da OBM 2011: reversed") {
    val expected = 24
    val actual = countChange(37,List(25,10,5,1))
    assert(actual === expected)
  }
  test("countChange: questão da OBM 2011: mixed") {
    val expected = 24
    val actual = countChange(37,List(1,10,25,5))
    assert(actual === expected)
  }
}

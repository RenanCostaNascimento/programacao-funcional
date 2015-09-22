package funsets

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

/**
 * Aluno: Renan Costa Nascimento
 *
 * Esta classe implementa um conjunto de testes para os métodos do
 * objeto FunSets. Para executar este conjunto de testes você deve
 * executar o comando `test` no console do SBT.
 */
@RunWith(classOf[JUnitRunner])
class FunSetSuite extends FunSuite {

  /** Link para o scaladoc - um tutorial bastante detalhado e claro
    * sobre FunSuite (em inglês).
    *
    * http://doc.scalatest.org/1.9.1/index.html#org.scalatest.FunSuite
    *
    * Veja os operadores:
    * - test
    * - ignore
    * - pending
    *
    * Consulte também o exemplo de testes dado no Trabalho 0.
    */

  import FunSets._

  test("contains foi implementado") {
    assert(contains(x => true, 100)) // Você sabe que conjunto é
    // definido por `x => true`?
  }

  /** Ao escrever testes é muito comum querer reusar certos valores para
    * múltiplos testes. Por exemplo, pode-se querer criar um IntSet e
    * fazer vários testes como ele.
    *
    * Ao invés de copiar e colar o código para criar os valores em
    * cada testes, pode-se armazenar os valores na classe de testes
    * usando definições `val`:
    *
    * val s1 = singletonSet(1)
    *
    * Entretanto, o que acontece se o método `singletonSet` tiver um
    * erro (bug) e abortar? Nesse caso os testes não serão nem
    * executados, porque a criação dos valores irá falha!
    *
    * Assim, é melhor colocar a criação dos valores para testes dentro
    * de um `trait` separado e criar uma instância do `trait` em cada
    * teste. Há um exemplo abaixo de como fazer isso.
    */

  trait TestSets {
    val s1 = singletonSet(1)
    val s2 = singletonSet(2)
    val s3 = singletonSet(3)
    val allPositive = (x: Int) => x > 0
    val positiveNegative = (x: Int) => List(-1, 1, -2, 2, -3, 3).contains(x)
    val allEven = (x: Int) => List(2, 4, 6, 8, 10).contains(x)
    val evenOdd = (x: Int) => List(2, 4, 6, 8, 10, 11).contains(x)
  }

  /** Este teste está desabilitado (usando a função `ignore`) porque o
    * método `singletonSet` ainda não foi implementado e o teste iria
    * falhar.
    *
    * Quando você terminar de implementar o `singletonSet`, troque a
    * função `ignore` por `test` para executar o teste.
    */
  test("singletonSet(1): contains 1") {

    /** Ao criar uma nova instância do trait `TestSets`, ganha-se acesso
      * ao valores `s1`, `s2` e `s3` que podem ser usados no teste.
      */
    new TestSets {
      assert(contains(s1, 1) === true)
    }
  }

  // Escreva o restante dos testes.

  test("singletonSet(1): does not contain 2 or 3") {

    new TestSets {
      assert(contains(s1, 2) === false)
      assert(contains(s1, 3) === false)
    }
  }

  test("union(1,2): should contain 1 and 2") {

    new TestSets {
      assert(contains(union(s1, s2), 1) === true)
      assert(contains(union(s1, s2), 2) === true)
    }
  }
  test("union(1,2): should not contain 3") {

    new TestSets {
      assert(contains(union(s1, s2), 3) === false)
    }
  }

  test("intersect(1,2): should be empty") {

    new TestSets {
      assert(contains(intersect(s1, s2), 1) === false)
      assert(contains(intersect(s1, s2), 2) === false)
      assert(contains(intersect(s1, s2), 3) === false)
    }
  }
  test("intersect(1,allPositive): should contain 1") {

    new TestSets {
      assert(contains(intersect(s1, allPositive), 1) === true)
    }
  }
  test("intersect(1,allPositive): should not contain 2 and 3") {

    new TestSets {
      assert(contains(intersect(s1, allPositive), 2) === false)
      assert(contains(intersect(s1, allPositive), 3) === false)
    }
  }

  test("diff(allPositive,1): should not contain 1") {

    new TestSets {
      assert(contains(diff(allPositive, s1), 1) === false)
    }
  }
  test("diff(allPositive,1): should contain 2 and 3") {

    new TestSets {
      assert(contains(diff(allPositive, s1), 2) === true)
      assert(contains(diff(allPositive, s1), 3) === true)
    }
  }

  test("filter(positiveNegative,allPositive): should contain 1, 2 and 3") {

    new TestSets {
      assert(contains(filter(positiveNegative, x => x > 0), 1) === true)
      assert(contains(filter(positiveNegative, x => x > 0), 2) === true)
      assert(contains(filter(positiveNegative, x => x > 0), 3) === true)
    }
  }
  test("filter(positiveNegative,allPositive): should not contain -1, -2 or -3") {

    new TestSets {
      assert(contains(filter(positiveNegative, x => x > 0), -1) === false)
      assert(contains(filter(positiveNegative, x => x > 0), -2) === false)
      assert(contains(filter(positiveNegative, x => x > 0), -3) === false)
    }
  }

  test("forall(positiveNegative,allPositive): should be false") {

    new TestSets {
      assert(forall(positiveNegative, x => x > 0) === false)
    }
  }
  test("forall(allPositive,allPositive): should be true") {

    new TestSets {
      assert(forall(allPositive, x => x > 0) === true)
    }
  }
  test("forall(allPositive,negative): should be false") {

    new TestSets {
      assert(forall(allPositive, x => x < 0) === false)
    }
  }
  test("forall(evenOdd,even): should be false") {

    new TestSets {
      assert(forall(evenOdd, x => x % 2 == 0) === false)
    }
  }
  test("forall(even,even): should be true") {

    new TestSets {
      assert(forall(allEven, x => x % 2 == 0) === true)
    }
  }

  test("exists(evenOdd,odd): should be true") {

    new TestSets {
      assert(exists(evenOdd, x => x % 2 != 0) === true)
    }
  }
  test("exists(positiveNegative,positive): should be true") {

    new TestSets {
      assert(exists(positiveNegative, x => x > 0) === true)
    }
  }
  test("exists(positiveNegative,negative): should be true") {

    new TestSets {
      assert(exists(positiveNegative, x => x < 0) === true)
    }
  }
  test("exists(allPositive,negative): should be false") {

    new TestSets {
      assert(exists(allPositive, x => x < 0) === false)
    }
  }

  test("existsForAll(evenOdd,odd): should be true") {

    new TestSets {
      assert(existsForAll(evenOdd, x => x % 2 != 0) === true)
    }
  }
  test("existsForAll(positiveNegative,positive): should be true") {

    new TestSets {
      assert(existsForAll(positiveNegative, x => x > 0) === true)
    }
  }
  test("existsForAll(positiveNegative,negative): should be true") {

    new TestSets {
      assert(existsForAll(positiveNegative, x => x < 0) === true)
    }
  }
  test("existsForAll(allPositive,negative): should be false") {

    new TestSets {
      assert(existsForAll(allPositive, x => x < 0) === false)
    }
  }

  test("map(s1,times 2): should contains only the original elements times 2") {

    new TestSets {
      val setTimesTwo = map(s1, x => x * 2)
      assert(contains(setTimesTwo, 2) === true)
      assert(contains(setTimesTwo, 1) === false)
    }
  }
  test("map(allEven,times 2): should contains only the original elements times 2") {

    new TestSets {
      val setTimesTwo = map(allEven, x => x * 2)
      assert(contains(setTimesTwo, 2) === false)
      assert(contains(setTimesTwo, 6) === false)
      assert(contains(setTimesTwo, 10) === false)

      assert(contains(setTimesTwo, 4) === true)
      assert(contains(setTimesTwo, 8) === true)
      assert(contains(setTimesTwo, 12) === true)
      assert(contains(setTimesTwo, 16) === true)
      assert(contains(setTimesTwo, 20) === true)
    }
  }
  test("map(positiveNegative,times 2): should contains only the original elements times 2") {

    new TestSets {
      val setTimesTwo = map(positiveNegative, x => x * 2)
      assert(contains(setTimesTwo, -1) === false)
      assert(contains(setTimesTwo, -3) === false)

      assert(contains(setTimesTwo, -2) === true)
      assert(contains(setTimesTwo, 2) === true)
      assert(contains(setTimesTwo, -4) === true)
      assert(contains(setTimesTwo, 4) === true)
      assert(contains(setTimesTwo, -6) === true)
      assert(contains(setTimesTwo, 6) === true)
    }
  }

  test("mapExists(s1,times 2): should contains only the original elements times 2") {

    new TestSets {
      val setTimesTwo = mapExistsForAll(s1, x => x * 2)
      assert(contains(setTimesTwo, 2) === true)
      assert(contains(setTimesTwo, 1) === false)
    }
  }
  test("mapExists(allEven,times 2): should contains only the original elements times 2") {

    new TestSets {
      val setTimesTwo = mapExistsForAll(allEven, x => x * 2)
      assert(contains(setTimesTwo, 2) === false)
      assert(contains(setTimesTwo, 6) === false)
      assert(contains(setTimesTwo, 10) === false)

      assert(contains(setTimesTwo, 4) === true)
      assert(contains(setTimesTwo, 8) === true)
      assert(contains(setTimesTwo, 12) === true)
      assert(contains(setTimesTwo, 16) === true)
      assert(contains(setTimesTwo, 20) === true)
    }
  }
  test("mapExists(positiveNegative,times 2): should contains only the original elements times 2") {

    new TestSets {
      val setTimesTwo = mapExistsForAll(positiveNegative, x => x * 2)
      assert(contains(setTimesTwo, -1) === false)
      assert(contains(setTimesTwo, -3) === false)

      assert(contains(setTimesTwo, -2) === true)
      assert(contains(setTimesTwo, 2) === true)
      assert(contains(setTimesTwo, -4) === true)
      assert(contains(setTimesTwo, 4) === true)
      assert(contains(setTimesTwo, -6) === true)
      assert(contains(setTimesTwo, 6) === true)
    }
  }

}

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
    val s4 = (x: Int) => x > 0
    val s5 = (x: Int) => List(-1, 1, -2, 2, -3, 3).contains(x)
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
  test("intersect(1,positiveIntegers): should contain 1") {

    new TestSets {
      assert(contains(intersect(s1, s4), 1) === true)
    }
  }
  test("intersect(1,positiveIntegers): should not contain 2 and 3") {

    new TestSets {
      assert(contains(intersect(s1, s4), 2) === false)
      assert(contains(intersect(s1, s4), 3) === false)
    }
  }

  test("diff(positiveIntegers,1): should not contain 1") {

    new TestSets {
      assert(contains(diff(s4, s1), 1) === false)
    }
  }
  test("diff(positiveIntegers,1): should contain 2 and 3") {

    new TestSets {
      assert(contains(diff(s4, s1), 2) === true)
      assert(contains(diff(s4, s1), 3) === true)
    }
  }

  test("filter(s5,positiveIntegers): should contain 1, 2 and 3") {

    new TestSets {
      assert(contains(filter(s5, s4), 1) === true)
      assert(contains(filter(s5, s4), 2) === true)
      assert(contains(filter(s5, s4), 3) === true)
    }
  }
  test("filter(s5,positiveIntegers): should not contain -1, -2 or -3") {

    new TestSets {
      assert(contains(filter(s5, s4), -1) === false)
      assert(contains(filter(s5, s4), -2) === false)
      assert(contains(filter(s5, s4), -3) === false)
    }
  }

}
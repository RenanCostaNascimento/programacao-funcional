package example

import java.util.NoSuchElementException

import org.junit.runner.RunWith
import org.scalatest.FunSuite
import org.scalatest.junit.JUnitRunner

/**
  * Esta class implementa um conjunto de testes ScalaTest para os
  * métodos do objeto `Lists` que precisa ser implementado como parte
  * de um trabalho de programação. Um conjunto de testes (test suit) é
  * simplesmente um conjunto de testes individuais para algum
  * componente específico de um programa.
  *
  * Um test suit é criado definindo-se uma classe que estenda a classe
  * `org.scalatest.FunSuit`. Ao executar a ferramenta ScalaTest ela
  * irá automaticamente encontrar estas classes e executar todos os
  * seus testes.
  *
  * Para executar este conjunto de testes, inicie o console do sbt e
  * execute o comando "test".
  */
@RunWith(classOf[JUnitRunner])
class ListsSuite extends FunSuite {

  /**
    * Testes são escritos usando-se o operador `test`, que recebe dois
    * argumentos:
    *
    * - Uma descrição textual do teste. Esta descrição tem que ser
    *   única para cada teste. Ou seja, não pode haver dois ou mais
    *   testes com a mesma descrição.
    * - O corpo do testes, composto por código Scala que implementa o
    *   teste.
    *
    * A forma mais comum de implementar o corpo do teste é
    * utilizando-se o método `assert` que testa se o seu argumento é
    * `true`. Então, um dos testes mais simples possível é o seguinte:
    */
  test("one plus one is two")(assert(1 + 1 == 2))


  /**
    * Em Scala, é permitido passar um argumento a um método
    * utilizando-se a sintaxe de bloco, i.e., `{ argumento }` ao invés
    * de parênteses.
    *
    * Isso permite escrever os testes de uma forma mais legível:
    */
  test("one plus one is three?") {
    assert(1 + 1 == 2) // Essa assertiva falha! Vá em frente e a concerte.
  }


  /**
    * Um problema com a abordagem do teste anterior é que o ScalaTest
    * vai apenas dizer que um teste falhor, mas não vai dizer qual foi
    * a razão da falha. A saída produzida pelo ScalaTest terá esta
    * forma:
    *
    * {{{
    *    [info] - one plus one is three? *** FAILED ***
    * }}}
    *
    * Esta situação pode ser um pouco melhorada usando-se o operador
    * especial de igualdade `===` ao invés do operador normal `==`
    * (isso só é possível usando o ScalaTest). Então, se você executar
    * o teste à seguir, o ScalaTest vai mostrar a saída abaixo:
    *
    * {{{
    *    [info] - details why one plus one is not three *** FAILED ***
    *    [info]   2 did not equal 3 (ListsSuite.scala:67)
    * }}}
    *
    * É recomendável usar sempre o operador de igualdade `===` ao
    * escrever testes.
    */
  test("details why one plus one is not three") {
    assert(1 + 1 === 2) // Fix me, please!
  }


  /**
    * Para permitir testar os comportamentos de exceção de um método,
    * o ScalaTest oferece a operação `intercept`.
    *
    * No exemplo abaixo, nós testamos o fato de que o método
    * `intNotZero` dispara a exceção `IllegalArgumentException` se o
    * seu argumento for `0`.
    */
  test("intNotZero throws an exception if its argument is 0") {
    intercept[IllegalArgumentException] {
      intNotZero(0)
    }
  }

  def intNotZero(x: Int): Int = {
    if (x == 0) throw new IllegalArgumentException("zero is not allowed")
    else x
  }


  /**
    * Agora nós finalmente podemos escrever alguns testes para as
    * funções the listas que tem que ser implementadas neste trabalho
    * de programação. Primeiramente nós precisamos importar todos os
    * métodos do objeto `Lists`.
    */
  import Lists._


  /**
    * São fornecidos apenas dois testes bastante básicos abaixo. Você
    * deve escrever mais testes para ter certeza que os métodos `sum`
    * e `max` funcionam como esperado.
    *
    * Em particular, escreva testes para os casos de contorno: números
    * negativos, zeros, listas vazias, listas com elementos repetidos,
    * etc.
    *
    * É permitido ter múltiplas declarações `assert` dentro de um
    * mesmo teste, entretanto, é recomendado escrever uma declaração
    * `test` individual para cada aspecto testado em um método.
    */
  test("sum of a few numbers") {
    assert(sum(List(1,2,0)) === 3)
  }
  test("sum of empty list") {
    assert(sum(List()) === 0)
  }
  test("sum with negative numbers") {
    assert(sum(List(1, 2, -3, 1, 0)) === 1)
  }

  test("max of a few numbers") {
    assert(max(List(3, 7, 2)) === 7)
  }
  test("max of empty list") {
    intercept[NoSuchElementException]{
      max(List())
    }
  }
  test("max of with negative numbers") {
    assert(max(List(-1, 3, 0, 7, 2, -8)) === 7)
  }
}

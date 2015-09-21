package funsets

import common._

/**
 * Aluno: Renan Costa Nascimento
 * 2. Conjuntos funcionais puros.
 */
object FunSets {
  /** Um conjunto é representado por sua função característica, i.e., o
    * seu predicado.
    */
  type Set = Int => Boolean

  /** Indica se um conjunto contém ou não um elemento.
    */
  def contains(s: Set, elem: Int): Boolean = s(elem)

  /** Retorna o conjunto que contém exatamente o elemento indicado.
    */
  def singletonSet(elem: Int): Set = (x: Int) => x == elem

  /** Retorna a união dos dois conjuntos dados, i.e., o conjunto de
    * todos os elementos que estão em `s` ou em `t`.
    */
  def union(s: Set, t: Set): Set = (x: Int) => s(x) || t(x)

  /** Retorna a interseção dos dois conjuntos dados, i.e., o conjunto
    * contendo todos os elementos que estão em `s` e em `t`.
    */
  def intersect(s: Set, t: Set): Set = (x: Int) => s(x) && t(x)

  /** Retorna a diferença entre os dois conjuntos dados, i.e., o
    * conjunto contendo todos os elementos que estão em `s` e não estão
    * em `t`.
    */
  def diff(s: Set, t: Set): Set = (x: Int) => s(x) && !t(x)

  /** Retorna o subconjunto de `s` para o qual `p` é verdadeiro.
    */
  def filter(s: Set, p: Int => Boolean): Set = (x: Int) => s(x) && p(x)

  /** Os limites para os testes das funções `forall` e `exists`
    * são +/- 1000.
    */
  val bound = 1000

  /** Retorna `true` se *todos* os inteiros (entre os limites
    * especificados por `bound`) dentro de `s` satisfazem o predicado
    * `p`; e `false` caso contrário.
    */
  def forall(s: Set, p: Int => Boolean): Boolean = {
    def iter(a: Int): Boolean = {
      if (a > bound) true
      else if (s(a) && !p(a)) false
      else iter(a + 1)
    }
    iter(-bound)
  }

  /** Retorna `true` se *algum* dos inteiros (entre os limites
    * especificados por `bound`) dentro de `s` satisfazem o predicado
    * `p`; e `false` caso contrário.
    */
  def exists(s: Set, p: Int => Boolean): Boolean = ???

  /** Retorna um conjunto transformado pela aplicação de `f` a cada
    * elemntos do conjunto `s`.
    */
  def map(s: Set, f: Int => Int): Set = ???

  /** Exibe o conteúdo de um conjunto -- dentro dos limites definidos
    * por `bound`.
    */
  def toString(s: Set): String = {
    val xs = for (i <- -bound to bound if contains(s, i)) yield i
    xs.mkString("{", ",", "}")
  }

  /** Imprime o conteúdo de um conjunto no console.
    */
  def printSet(s: Set) {
    println(toString(s))
  }
}

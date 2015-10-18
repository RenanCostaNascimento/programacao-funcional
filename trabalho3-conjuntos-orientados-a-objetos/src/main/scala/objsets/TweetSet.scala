package objsets

/**
 * Classe que representa tweets.
 */
class Tweet(val user: String, val text: String, val retweets: Int) {
  override def toString: String =
    "User: " + user + "\n" +
      "Text: " + text + " [" + retweets + "]"
}

/**
 * Classe que representa um conjunto de objetos do tipo `Tweet` na
 * forma de uma árvore binária de busca. Cada ramo na árvore tem dois
 * filhos (dois `TweetSet`s). Existe uma invariante que é sempre
 * verdadeira: para cada ramo `b`, todos os elementos na sub-árvore
 * esquerda são menores que o tweet na raiz de `b`. Os elementos na
 * sub-árvore direita são maiores.
 *
 * Note que a estrutura acima exige que nós sejamos capazes de
 * comparar tweets (nós precisamos poder dizer qual dentre dois
 * tweets é menor, maior ou se são iguais). Nesta implementação, a
 * igualdade ou ordem dos tweets é baseada no texto do
 * tweet. Portanto, um `TweetSet` não pode conter dois tweets com
 * exatamente o mesmo texto de dois usuários diferentes.
 *
 * A vantagem de representar conjuntos como árvores binárias de
 * busca é que os elementos no conjunto podem ser pesquisados
 * rapidamente. Se você quiser aprender mais sobre esse assunto, dê
 * uma olhada na página da Wikipédia sobre árvores binárias de
 * pesquisa [1,2].
 *
 * [1] http://en.wikipedia.org/wiki/Binary_search_tree
 * [2] http://pt.wikipedia.org/wiki/%C3%81rvore_bin%C3%A1ria_de_busca
 */
abstract class TweetSet {

  /** Este método recebe um predicado e retorna um sub-conjunto
    * contendo todos os elementos do conjunto original para os quais o
    * predicado é verdadeiro.
    *
    * Pergunta: Esse método pode ser implementado aqui, ou deve ser
    * deixado abstrato e implementado nas sub-classes concretas?
    */
  def filter(p: Tweet => Boolean): TweetSet = filterAcc(p, Empty)

  /** Este é um método auxiliar para `filter` que propaca os tweets
    * acumulados.
    */
  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet

  /** Retorna um novo `TweetSet` que é a união dos conjuntos `this` e
    * `that`.
    *
    * Pergunta: Esse método deve ser implementado aqui, ou deve
    * permanecer abstrato e ser implementado nas sub-classes?
    */
  def union(that: TweetSet): TweetSet

  /** Retorna o tweet neste conjunto que tem a maior contagem de
    * retweets.
    *
    * Chamar `mostRetweeted` em um conjunto vazio deve gerar uma
    * exceção do tipo `java.util.NoSuchElementException`.
    *
    * Pergunta: Esse método deve ser implementado aqui, ou deve
    * permanecer abstrato e ser implementado nas sub-classes?
    */
  def mostRetweeted: Tweet = ???

  /** Retorna uma lista contendo todos os tweets deste conjunto,
    * ordenados pela contagem de retweets em ordem
    * decrescente -- i.e., o primeiro é o tweet com mais retweets e o
    * último é o com menos.
    *
    * Dica: o método `remove` será muito útil. :-)
    *
    * Pergunta: Esse método deve ser implementado aqui, ou deve
    * permanecer abstrato e ser implementado nas sub-classes?
    */
  def descendingByRetweet: TweetList = ???


  /* ===================================================================
   * Os métodos abaixo já estão implementados.
   */

  /** Retorna um novo `TweetSet` que contém todos os elementos deste
    * conjunto, e inclui o novo elemento `tweet` caso este ainda não
    * exista no conjunto original.
    *
    * Se `this contains tweet` for verdadeiro o conjunto original é
    * retornado.
    */
  def incl(tweet: Tweet): TweetSet

  /** Retorna um novo `TweetSet` que exclui `tweet`.
    */
  def remove(tweet: Tweet): TweetSet

  /** Testa se `tweet` existe neste `TweetSet`.
    */
  def contains(tweet: Tweet): Boolean

  /** Este método recebe uma função e aplica esta função a todos os
    * elementos deste conjunto.
    */
  def foreach(f: Tweet => Unit): Unit
}

/** Objeto que representa um conjunto de tweets vazio.
  */
object Empty extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = acc

  def union(that: TweetSet): TweetSet = that

  /* ===================================================================
   * Os métodos abaixo já estão implementados.
   */

  def contains(tweet: Tweet): Boolean = false

  def incl(tweet: Tweet): TweetSet = new NonEmpty(tweet, Empty, Empty)

  def remove(tweet: Tweet): TweetSet = this

  def foreach(f: Tweet => Unit): Unit = ()
}

/** Classe que representa um conjunto não-vazio, i.e., contento ao
  * menos um elemento, de tweets.
  */
class NonEmpty(elem: Tweet, left: TweetSet, right: TweetSet) extends TweetSet {

  def filterAcc(p: Tweet => Boolean, acc: TweetSet): TweetSet = {
    if (p(elem)) right.filterAcc(p, left.filterAcc(p, acc.incl(elem)))
    else right.filterAcc(p, left.filterAcc(p, acc))
    // SAME AS ---->
    //    if (p(elem)) {
    //      val elemSet = acc.incl(elem)
    //      val leftSet = left.filterAcc(p, elemSet)
    //      right.filterAcc(p, leftSet)
    //    } else {
    //      val leftSet = left.filterAcc(p, acc)
    //      right.filterAcc(p, leftSet)
    //    }
  }

  def union(that: TweetSet): TweetSet = ???

  /* ===================================================================
   * Os métodos abaixo já estão implementados.
   */

  def contains(x: Tweet): Boolean =
    if (x.text < elem.text) left.contains(x)
    else if (elem.text < x.text) right.contains(x)
    else true

  def incl(x: Tweet): TweetSet = {
    if (x.text < elem.text) new NonEmpty(elem, left.incl(x), right)
    else if (elem.text < x.text) new NonEmpty(elem, left, right.incl(x))
    else this
  }

  def remove(tw: Tweet): TweetSet =
    if (tw.text < elem.text) new NonEmpty(elem, left.remove(tw), right)
    else if (elem.text < tw.text) new NonEmpty(elem, left, right.remove(tw))
    else left.union(right)

  def foreach(f: Tweet => Unit): Unit = {
    f(elem)
    left.foreach(f)
    right.foreach(f)
  }
}


/** Trait que representa uma lista simplesmente encadeada de tweets.
  */
trait TweetList {
  def head: Tweet

  def tail: TweetList

  def isEmpty: Boolean

  def foreach(f: Tweet => Unit): Unit =
    if (!isEmpty) {
      f(head)
      tail.foreach(f)
    }
}

object Nil extends TweetList {
  def head = throw new java.util.NoSuchElementException("head of EmptyList")

  def tail = throw new java.util.NoSuchElementException("tail of EmptyList")

  def isEmpty = true
}

case class Cons(head: Tweet, tail: TweetList) extends TweetList {
  def isEmpty = false
}


/** Objeto que encapsula os dados referentes aos tweets do Google e da
  * Apple.
  */
object GoogleVsApple {
  val google = List("android", "Android", "galaxy", "Galaxy", "nexus", "Nexus")
  val apple = List("ios", "iOS", "iphone", "iPhone", "ipad", "iPad")

  lazy val googleTweets: TweetSet = ???
  lazy val appleTweets: TweetSet = ???

  /** Uma lista de todos os tweets mencionando uma palavra chave de
    * qualquer das duas listas, apple ou google, ordenada pelo número
    * de retweets.
    */
  lazy val trending: TweetList = ???
}


object Main extends App {

  // Imprime os “trending tweets” (tweets populares?)
  //GoogleVsApple.trending foreach println
  trait TestSets {
    val set1 = Empty
    val set2 = set1.incl(new Tweet("a", "a body", 20))
    val set3 = set2.incl(new Tweet("b", "b body", 20))
    val c = new Tweet("c", "c body", 7)
    val d = new Tweet("d", "d body", 9)
    val set4c = set3.incl(c)
    val set4d = set3.incl(d)
    val set5 = set4c.incl(d)
  }

  new TestSets {
    set5.filter(tw => tw.user == "a")
  }

}

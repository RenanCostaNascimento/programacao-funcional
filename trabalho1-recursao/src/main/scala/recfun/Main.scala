package recfun

import common._

object Main {

  def main(args: Array[String]) {
    println(balance("()()".toList))
    println("END")
  }

  /**
   * Exercício 1
   */
  def pascal(c: Int, r: Int): Int = {
    if (c == 0 || c == r) {
      1
    } else {
      pascal(c - 1, r - 1) + pascal(c, r - 1)
    }
  }

  /**
   * Exercício 2
   */
  def balance(chars: List[Char]): Boolean = {
    if (chars.isEmpty) throw new NoSuchElementException
    def balanceIter(chars: List[Char], sumParen: Int): Boolean = {

      if (sumParen < 0) {
        false
      }
      if (chars.isEmpty) {
        sumParen == 0
      } else {
        if (chars.head == '(') {
          balanceIter(chars.tail, sumParen + 1)
        } else {
          if (chars.head == ')') {
            balanceIter(chars.tail, sumParen - 1)
          } else {
            balanceIter(chars.tail, sumParen)
          }
        }
      }
    }

    balanceIter(chars, 0)
  }

  /**
   * Exercício 3
   */
  def countChange(money: Int, coins: List[Int]): Int = ???
}

package recfun

import common._

/**
 * Aluno: Renan Costa Nascimento
 */
object Main {

  def main(args: Array[String]) {

    println(countChange(37, List(25, 10, 5, 1)))
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

      !(sumParen < 0)
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
  def countChange(money: Int, coins: List[Int]): Int = {
    def countChangeIter(money: Int, coins: List[Int]): Int = {
      if (money == 0) {
        1
      } else {
        if (money > 0 && !coins.isEmpty) {
          countChangeIter(money - coins.head, coins) + countChange(money, coins.tail)
        } else {
          0
        }
      }
    }

    if (money == 0) {
      0
    } else {
      countChangeIter(money, coins)
    }
  }
}
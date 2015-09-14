package recfun

import common._

/**
 * Aluno: Renan Costa Nascimento
 */
object Main {

  def main(args: Array[String]) {

    print(countChange(3, List(1,2)))
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
  def countChange(money: Int, coins: List[Int]): Int = {
    def countChangeIter(money: Int, coins: List[Int], change: List[Int], changeCombination: Int): Int = {

      def sum(xs: List[Int]): Int = {
        def sumIter(xs: List[Int], acumulador: Int): Int = {
          if (xs.isEmpty) acumulador else sumIter(xs.tail, acumulador + xs.head)
        }

        sumIter(xs, 0)
      }

      if (coins.isEmpty) {
        changeCombination
      } else {
        if (sum(change) == money) {
          if (coins.head == change.head) {
            countChangeIter(money, coins.tail, change.tail, changeCombination + 1)
          } else {
            countChangeIter(money, coins, change.tail, changeCombination + 1)
          }
        } else {
          if (sum(change) + coins.head <= money) {
            countChangeIter(money, coins, change ::: List(coins.head), changeCombination)
          } else {
            countChangeIter(money, coins, change.tail, changeCombination)
          }

        }
      }
    }

    if (money == 0) {
      0
    } else {
      countChangeIter(money, coins, List(), 0)
    }
  }


}

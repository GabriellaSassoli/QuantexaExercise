package services

import filesHandlers.ReadInput.readInput
import org.scalatest.{FlatSpec, Matchers}
import services.Question1.exerciseSolver

class Question1Test extends FlatSpec with Matchers {

  "getTotalTransactionByDay" should "return total transaction by day" in {
    val input = readInput("transactionTest.txt")
    exerciseSolver(input) shouldBe Map(1-> 338.11, 2 -> 516.34, 4 ->243.39)
  }

}

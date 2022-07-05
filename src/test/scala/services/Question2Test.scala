package services

import filesHandlers.ReadInput.readInput
import org.scalatest.{FlatSpec, Matchers}
import services.Question2.{calculateAverage, getTransactionsByUserAndCategory}

import scala.collection.immutable.HashMap

class Question2Test extends FlatSpec with Matchers {

  "getTransactionsByUserAndCategory" should "group transaction by userId and Category" in{
    getTransactionsByUserAndCategory("transactionTest.txt") shouldBe HashMap("A27" -> HashMap("GG" -> 338.11), "A39" -> HashMap("BB" -> 243.39), "A13" -> HashMap("FF" -> 516.34))
  }

  "calculateAverage" should "calculate average" in {
    val input = readInput("transactionTest.txt")
    calculateAverage(input) shouldBe 365.95
  }

}

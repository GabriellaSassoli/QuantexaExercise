package quantexa.services

import org.scalatest.{FlatSpec, Matchers}
import quantexa.filesHandlers.ReadInput.readInput
import quantexa.services.Question2.{calculateAverage, getTransactionsByUserAndCategory}

import scala.collection.immutable.HashMap

class Question2Test extends FlatSpec with Matchers {

  "getTransactionsByUserAndCategory" should "group transaction by userId and Category" in{
    val input = readInput("transactionTest.txt")
    val keys = input.groupBy(_.category).keys.toList
    getTransactionsByUserAndCategory(input,keys) shouldBe HashMap("A27" -> HashMap("BB" -> 0.0, "FF" -> 0.0, "GG" -> 338.11), "A39" -> HashMap("BB" -> 243.39, "FF" -> 0.0, "GG" -> 0.0), "A13" -> HashMap("BB" -> 0.0, "FF" -> 516.34, "GG" -> 0.0))
  }

  "calculateAverage" should "calculate average" in {
    val input = readInput("transactionTest.txt")
    calculateAverage(input) shouldBe 365.9466666666667
  }



}

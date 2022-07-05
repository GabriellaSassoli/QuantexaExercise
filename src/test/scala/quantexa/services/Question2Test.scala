package quantexa.services

import quantexa.filesHandlers.ReadInput.readInput
import quantexa.model.TransactionTypes
import org.scalatest.{FlatSpec, Matchers}
import quantexa.services.Question2.{calculateAverage, getTransactionsByUserAndCategory, convertToTransactionTypesCaseClass, unApply}

import scala.collection.immutable.HashMap

class Question2Test extends FlatSpec with Matchers {

  "getTransactionsByUserAndCategory" should "group transaction by userId and Category" in{
    val input = readInput("transactionTest.txt")
    getTransactionsByUserAndCategory(input) shouldBe HashMap("A27" -> HashMap("GG" -> 338.11), "A39" -> HashMap("BB" -> 243.39), "A13" -> HashMap("FF" -> 516.34))
  }

  "calculateAverage" should "calculate average" in {
    val input = readInput("transactionTest.txt")
    calculateAverage(input) shouldBe 365.9466666666667
  }

  "putValuesToTransactionType" should "put data into TransactionType case class from map based on accountID" in {
    val input = HashMap("A27" -> HashMap("GG" -> 338.11), "A39" -> HashMap("BB" -> 243.39), "A13" -> HashMap("FF" -> 516.34))
    convertToTransactionTypesCaseClass(input) shouldBe HashMap("A27" -> TransactionTypes(0.0,0.0,0.0,0.0,0.0,0.0,338.11), "A39" -> TransactionTypes(0.0,243.39,0.0,0.0,0.0,0.0,0.0), "A13" -> TransactionTypes(0.0,0.0,0.0,0.0,0.0,516.34,0.0))
  }

  "unapply" should "put data into TransactionType case class from map" in {
    val input = HashMap("GG" -> 338.11,"BB" -> 243.39, "FF" -> 516.34)
    unApply(input) shouldBe TransactionTypes( 0,243.39, 0,0,0,516.34,338.11)
  }

}

package quantexa.filesHandlers

import quantexa.filesHandlers.ReadInput.readInput
import quantexa.model.Transaction
import org.scalatest.{FlatSpec, Matchers}

class ReadInputTest extends FlatSpec with Matchers {

  "readInput" should "read input from file" in {
    readInput("transactionTest.txt") shouldBe List(Transaction("T0001", "A27", 1, "GG", 338.11), Transaction("T00077", "A13", 2, "FF", 516.34), Transaction("T000131", "A39", 4, "BB", 243.39))
  }

  it should "Throw an error in case empty lines added" in {

    val caught = intercept[Exception] {
      readInput("wrongText.txt")
    }
    caught.getMessage should contain("Failed to process file wrongText.txt at line 1 because of Index 1")
  }

  it should "Throw an error in case number of lines of a column is incorrect" in {

    val caught = intercept[Exception] {
      readInput("wrongNumbersOfColumns.txt")
    }
    caught.getMessage should contain("Failed to process file wrongNumbersOfColumns.txt at line 1 because of Index 4")
  }

  it should "Throw an error in case type of a value in a column is incorrect" in {

    val caught = intercept[Exception] {
      readInput("wrongTypes.txt")
    }
    caught.getMessage shouldBe "Failed to process file wrongTypes.txt at line 1 because of For input string: \"A\""
  }

}

package filesHandlers

import filesHandlers.ReadInput.readInput
import model.Transaction
import org.scalatest.{FlatSpec, Matchers}

class ReadInputTest extends FlatSpec with Matchers {

  "readInput" should "read input from file" in {
    readInput("transactionTest.txt") shouldBe List(Transaction("T0001","A27",1,"GG",338.11), Transaction("T00077","A13",2,"FF",516.34), Transaction("T000131","A39",4,"BB",243.39))
  }

  it should "Throw an error in case no file found" in {

    val caught = intercept[Exception] {
      readInput("")
    }

    caught.getMessage.contains("Error reading file") shouldBe true

  }

}

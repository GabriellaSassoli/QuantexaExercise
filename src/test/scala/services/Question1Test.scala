package services

import org.scalatest.{FlatSpec, Matchers}
import services.Question1.getTotalTransactionByDay

class Question1Test extends FlatSpec with Matchers {

  "getTotalTransactionByDay" should "return total transaction by day" in {
    getTotalTransactionByDay("transactionTest.txt") shouldBe Map(1-> 338.11, 2 -> 516.34, 4 ->243.39)
  }



}

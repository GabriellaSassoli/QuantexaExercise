package services

import model.{CalculatedStatistics, Transaction}
import org.scalatest.{FlatSpec, Matchers}
import services.Question3.{calcStats, exerciseSolver, getMaxDay, getRollingTransactionById, sumByCategory}

import scala.collection.immutable.HashMap

class Question3Test extends FlatSpec with Matchers {

  "getMaxDay" should "return max day in input list" in {
    val input = List(Transaction("T0001","A27",1,"GG",338.11), Transaction("T00077","A13",2,"FF",516.34), Transaction("T000131","A39",4,"BB",243.39))
    getMaxDay(input) shouldBe 4
  }


  "getRollingTransactionById" should "return rolling transaction" in {
    val input = List(Transaction("T0001","A27",1,"GG",338.11), Transaction("T00077","A13",2,"FF",516.34), Transaction("T000131","A27",4,"BB",243.39))
    val day = 7

    getRollingTransactionById(input,day) shouldBe HashMap("A27" -> List(Transaction("T000131","A27",4,"BB",243.39)), "A13" -> List(Transaction("T00077","A13",2,"FF",516.34)))
  }

  "calcStats" should "return a map of statics by userID" in {
    val input = List(Transaction("T000131","A27",4,"BB",3),Transaction("T000131","A27",5,"BB",2),Transaction("T000131","A27",6,"CC",2),Transaction("T00077","A13",2,"FF",516.34))
    calcStats(input,6) shouldBe Map("A27" -> CalculatedStatistics(3.0,0.0,0.0,0.0,2.5), "A13" -> CalculatedStatistics(516.34,0.0,0.0,516.34,516.34))
  }

  "sumByCategory" should "return the some of input by category" in {
    val input = HashMap("A27" -> List(Transaction("T000131","A27",4,"BB",3),Transaction("T000131","A27",5,"BB",2),Transaction("T000131","A27",5,"CC",2)), "A13" -> List(Transaction("T00077","A13",2,"FF",516.34)))
      sumByCategory(input, "BB") shouldBe Map("A27" -> 5.0, "A13" -> 0.0)
  }

  "exerciseSolver" should "return rolling calculate statistic" in {
    val input = List(Transaction("T000131","A27",4,"BB",3),Transaction("T000131","A27",5,"BB",2),Transaction("T000131","A27",6,"CC",2),Transaction("T00077","A13",2,"FF",516.34))
    exerciseSolver(input) shouldBe List((5,Map("A27" -> CalculatedStatistics(3.0,0.0,0.0,0.0,3.0), "A13" -> CalculatedStatistics(516.34,0.0,0.0,516.34,516.34))), (6,Map("A27" -> CalculatedStatistics(3.0,0.0,0.0,0.0,2.5), "A13" -> CalculatedStatistics(516.34,0.0,0.0,516.34,516.34))))

  }

}

package model

//import com.typesafe.scalalogging.LazyLogging
//import com.typesafe.config.{Config, ConfigFactory}
trait FinalResponse[T]{
  def exerciseSolver(transactions: List[Transaction]): T
  def writeFile(exerciseSolution: T, fileName: String): Unit
}

case class Transaction(transactionId: String, accountId: String, transactionDay: Int, category: String, transactionAmount: Double)

case class TransactionTypes(AA: BigDecimal, BB: BigDecimal, CC: BigDecimal, DD: BigDecimal,EE: BigDecimal,FF: BigDecimal,GG: BigDecimal)

case class CalculatedStatistics(max: Double, average: Double, AATotalValue: Double, CCTotalValue: Double, FFTotalValue: Double)



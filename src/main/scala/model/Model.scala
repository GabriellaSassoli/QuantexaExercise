package model

//import com.typesafe.scalalogging.LazyLogging
//import com.typesafe.config.{Config, ConfigFactory}
trait FinalResponse[T]{
  def exerciseSolver(transactions: List[Transaction]): T
  def writeFile(exerciseSolution: T, fileName: String): Unit
}

case class Transaction(transactionId: String, accountId: String, transactionDay: Int, category: String, transactionAmount: Double)

case class TransactionTypes(AA: Double, BB: Double, CC: Double, DD: Double,EE: Double,FF: Double,GG: Double)

case class CalculatedStatistics(max: Double, average: Double, AATotalValue: Double, CCTotalValue: Double, FFTotalValue: Double)



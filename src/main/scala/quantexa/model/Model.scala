package quantexa.model

import quantexa.TransactionsMain.transactions
import quantexa.services.Question1

import java.io.{BufferedWriter, File, FileWriter}

//import com.typesafe.scalalogging.LazyLogging
//import com.typesafe.config.{Config, ConfigFactory}
trait FinalResponse[T]{
  def name: String
  def exerciseSolver(transactions: List[Transaction]): T
  def exerciseFormatter(exerciseSolution: T): String
  def writeFile(content: String): Unit = {
    val file = new File(name)
    val bw = new BufferedWriter(new FileWriter(file))

    bw.write(content)
    bw.close()
  }

  def solveExercise(transactions: List[Transaction]) =
    writeFile(exerciseFormatter(exerciseSolver(transactions)))
}

case class Transaction(transactionId: String, accountId: String, transactionDay: Int, category: String, transactionAmount: Double)

case class TransactionTypes(AA: Double, BB: Double, CC: Double, DD: Double,EE: Double,FF: Double,GG: Double)

case class CalculatedStatistics(max: Double, average: Double, AATotalValue: Double, CCTotalValue: Double, FFTotalValue: Double)



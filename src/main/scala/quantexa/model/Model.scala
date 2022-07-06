package quantexa.model

import java.io.{BufferedWriter, File, FileWriter}

trait FinalResponse[T] {
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

  def round(value: Double) =
    BigDecimal(value).setScale(2, BigDecimal.RoundingMode.HALF_UP).toDouble

}

case class Transaction(transactionId: String, accountId: String, transactionDay: Int, category: String, transactionAmount: Double)

case class CalculatedStatistics(max: Double, average: Double, AATotalValue: Double, CCTotalValue: Double, FFTotalValue: Double)



package quantexa.services

import quantexa.model.{FinalResponse, Transaction, TransactionTypes}

import java.io.{BufferedWriter, File, FileWriter}

object Question2 extends FinalResponse[Map[String, TransactionTypes]] {

  def getTransactionsByUserAndCategory(allTransactions: List[Transaction]): Map[String, Map[String, Double]] = {

    allTransactions.groupBy(_.accountId).map {
      case (accountId, transactions) => accountId ->
        transactions.groupBy(_.category)
          .map { case (category, ts) =>
            category -> calculateAverage(ts)
          }
    }
  }

  def calculateAverage(data: List[Transaction]): Double =
    data.collect(_.transactionAmount).sum / data.length

  def convertToTransactionTypesCaseClass(data: Map[String, Map[String, Double]]): Map[String, TransactionTypes] =
    data.map {
      case (accountId, transaction) => accountId -> unApply(transaction)
    }

  def unApply(ts: Map[String, Double]): TransactionTypes =
    TransactionTypes(ts.getOrElse("AA", 0), ts.getOrElse("BB", 0), ts.getOrElse("CC", 0), ts.getOrElse("DD", 0), ts.getOrElse("EE", 0), ts.getOrElse("FF", 0), ts.getOrElse("GG", 0))

  override def exerciseSolver(transactions: List[Transaction]): Map[String, TransactionTypes] =
    convertToTransactionTypesCaseClass(getTransactionsByUserAndCategory(transactions))

  override def writeFile(data: Map[String, TransactionTypes], fileName: String) = {
    val file = new File(fileName)

    val bw = new BufferedWriter(new FileWriter(file))
    data.foreach {
      case (accountID, transactionTypes) => bw.write(s"accountId: $accountID, average by transactionType: $transactionTypes\n")
    }
    bw.close()
  }

}

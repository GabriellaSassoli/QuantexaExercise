package services

import model.{FinalResponse, Transaction, TransactionTypes}

import java.io.{BufferedWriter, File, FileWriter}

object Question2 extends FinalResponse[Map[String,TransactionTypes]]{

  type TransactionByCategory = Map[String, List[Transaction]]

  def getTransactionsByUserAndCategory(transactions: List[Transaction]): Map[String, Map[String, Double]] = {
//    println(transactions.groupMapReduce(_.accountId)(_.category)((category,ts) => calculateAverage(ts))

    transactions.groupBy(_.accountId).map {
      case (accountId, transaction) => accountId ->
        transaction.groupBy(_.category)
          .map { case (category, ts) =>
            category -> calculateAverage(ts)
          }
    }

  }

  def calculateAverage(data: List[Transaction]): Double = {
    val average = data.collect(_.transactionAmount).sum / data.length
    average
  }

  def convertToTransactionTypesCaseClass(data: Map[String, Map[String, Double]]): Map[String, TransactionTypes] =
    data.map {
      case (accountId, transaction) => accountId -> unApply(transaction)
    }

  def unApply(ts: Map[String,Double]): TransactionTypes =
    TransactionTypes(ts.getOrElse("AA",0), ts.getOrElse("BB",0),ts.getOrElse("CC",0),ts.getOrElse("DD",0),ts.getOrElse("EE",0),ts.getOrElse("FF",0),ts.getOrElse("GG",0))

  override def exerciseSolver(transactions: List[Transaction]): Map[String, TransactionTypes] =
    convertToTransactionTypesCaseClass(getTransactionsByUserAndCategory(transactions))

  override def writeFile(data: Map[String,TransactionTypes], fileName: String) = {
    val file = new File(fileName)

    val bw = new BufferedWriter(new FileWriter(file))
    data.foreach{
      case (accountID,transactionTypes) => bw.write(s"accountId: $accountID, average by transactionType: $transactionTypes\n")
    }
    bw.close()
  }

}

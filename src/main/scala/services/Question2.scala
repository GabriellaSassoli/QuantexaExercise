package services

import filesHandlers.ReadInput.{getInput, readInput}
import filesHandlers.WriteFile.writeFile
import model.{Transaction, TransactionTypes}
import services.Question1.getTotalTransactionByDay

import java.io.{BufferedWriter, File, FileWriter}

object Question2 {

  type TransactionByCategory = Map[String, List[Transaction]]

  def getTransactionsByUserAndCategory = {
    getInput().groupBy(_.accountId).map {
      case (accountId, transaction) => accountId ->
        transaction.groupBy(_.category)
          .map { case (category, transactions) =>
            category -> calculateAverage(transactions) }
    }
  }

  def calculateAverage(data: List[Transaction]): Double = data.collect(_.transactionAmount).sum / data.length

  // To improve this function putting map to case class. I have done this because not every transaction category is always taken into consideration. So needed to accounter to add a 0 field if that was the case. Also it makes it easier to print
  def putValuesToTransactionType(data: Map[String, Map[String, Double]]): Map[String, TransactionTypes] =
    data.map {
      case (accountId, transaction) => accountId ->
        TransactionTypes(transaction.getOrElse("AA", 0), transaction.getOrElse("BB", 0), transaction.getOrElse("CC", 0), transaction.getOrElse("DD", 0), transaction.getOrElse("EE", 0), transaction.getOrElse("FF", 0), transaction.getOrElse("GG", 0))
    }



  def writeTransactionByAccount = writeFile(putValuesToTransactionType(getTransactionsByUserAndCategory), "exercise2")


  //This should be merged together with the print file for exercise1
  def writeFile(data: Map[String,TransactionTypes],fileName: String = "exercise2.txt") = {
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    data.foreach{
      case (accountId, transactionTypes) => bw.write(s"accountId: $accountId, average by transactionType: $transactionTypes\n")
    }
    bw.close()
  }
  }


package services

import filesHandlers.ReadInput.{getInput, readInput}
import filesHandlers.WriteFile.writeFile
import model.TotalValue

object Question1 {

  def getTotalTransactionByDay(filename: String = "transaction.txt"): Map[Int, TotalValue] = {
    getInput(filename).groupBy(_.transactionDay).map{case (day,transactions) =>
      (day, TotalValue(transactions.collect(_.transactionAmount).sum))
    }
  }

  def writeTransactionByDay = writeFile(getTotalTransactionByDay(), "question1")

}

package services

import filesHandlers.ReadInput.{getInput, readInput}
import filesHandlers.WriteFile.writeFile

object Question1 {

  def getTotalTransactionByDay(filename: String = "transaction.txt"): Map[Int, Double] = {
    getInput().groupBy(_.transactionDay).map{case (day,transactions) =>
      (day, transactions.collect(_.transactionAmount).sum)
    }
  }

  def writeTransactionByDay = writeFile(getTotalTransactionByDay(), "exercise1")

}

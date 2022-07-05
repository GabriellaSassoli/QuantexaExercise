package quantexa.services

import quantexa.model.{FinalResponse, Transaction}

object Question2 extends FinalResponse[Map[String, Map[String, Double]]] {

  def getTransactionsByUserAndCategory(allTransactions: List[Transaction], allCategories: List[String]): Map[String, Map[String, Double]] =
    allTransactions.groupBy(_.accountId).map {
      case (accountId, transactions) =>
        val avgByCategory = transactions.groupBy(_.category)
          .map { case (category, ts) =>
            category -> calculateAverage(ts)
          }
        val missingCategories = (allCategories diff avgByCategory.keys.toList).map(cat => cat -> 0.0).toMap
        accountId -> (avgByCategory ++ missingCategories)
    }

  def calculateAverage(data: List[Transaction]): Double =
    data.collect(_.transactionAmount).sum / data.length

  override def exerciseSolver(transactions: List[Transaction]): Map[String, Map[String, Double]] = {
    val categories = transactions.groupBy(_.category).keys
    getTransactionsByUserAndCategory(transactions, categories.toList)
  }

  override def exerciseFormatter(exerciseSolution: Map[String, Map[String, Double]]): String =
    exerciseSolution.map {
      case (accountID, transactionTypes) => s"accountId: $accountID, average by transactionType: $transactionTypes"
    }.mkString("\n")

  override def name: String = "question2Solution"
}

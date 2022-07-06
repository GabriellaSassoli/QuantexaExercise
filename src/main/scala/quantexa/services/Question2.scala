package quantexa.services

import quantexa.{AccountId, Average, Category}
import quantexa.model.{FinalResponse, Transaction}
import scala.math._

object Question2 extends FinalResponse[Map[AccountId, Map[Category, Average]]] {

  def getTransactionsByUserAndCategory(allTransactions: List[Transaction], allCategories: List[String]): Map[AccountId, Map[Category, Average]] =
    allTransactions.groupBy(_.accountId).map {
      case (accountId, transactions) =>
        val avgByCategory = transactions.groupBy(_.category)
          .map { case (category, ts) =>
            category -> calculateAverage(ts)
          }
        val missingCategories: Map[Category, Average] = (allCategories diff avgByCategory.keys.toList).map(cat => cat -> 0.0).toMap
        accountId -> (avgByCategory ++ missingCategories)
    }

  def calculateAverage(data: List[Transaction]): Average =
    round(data.collect(_.transactionAmount).sum / data.length)

  override def exerciseSolver(transactions: List[Transaction]): Map[AccountId, Map[Category, Average]] = {
    val categories = transactions.groupBy(_.category).keys
    getTransactionsByUserAndCategory(transactions, categories.toList)
  }

  override def exerciseFormatter(exerciseSolution: Map[AccountId, Map[Category, Average]]): String =
    exerciseSolution.map {
      case (accountID, transactionTypes) => s"accountId: $accountID, average by transactionType: $transactionTypes"
    }.mkString("\n")

  override def name: String = "question2Solution"
}

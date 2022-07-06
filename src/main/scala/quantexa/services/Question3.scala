package quantexa.services

import quantexa.{AccountId, DayNumber}
import quantexa.model.{CalculatedStatistics, FinalResponse, Transaction}
import scala.math._

object Question3 extends FinalResponse[List[(DayNumber, Map[AccountId, CalculatedStatistics])]] {

  def exerciseSolver(ts: List[Transaction]): List[(DayNumber, Map[AccountId, CalculatedStatistics])] =
    (0 to getMaxDay(ts)).map { day =>
      day -> calcStats(ts, day)
    }.toList

  def getMaxDay(ts: List[Transaction]): Int =
    ts.collect(_.transactionDay).max

  def calcStats(transactions: List[Transaction], day: DayNumber): Map[AccountId, CalculatedStatistics] = {

    // all transactions for date range, grouped by accountID
    val transactionsById: Map[AccountId, List[Transaction]] = getRollingTransactionById(transactions, day)

    // "AA", "CC", "FF" transactions sum by account id
    val aaSums = sumByCategory(transactionsById, "AA")
    val ccSums = sumByCategory(transactionsById, "CC")
    val ffSums = sumByCategory(transactionsById, "FF")

    // transactions by accountId
    val transactionsByAmount: Map[AccountId, List[Double]] = transactionsById.view.mapValues(_.map(_.transactionAmount)).toMap

    // count of all transactions by account id
    val allCounts = transactionsById.view.mapValues(_.length)

    // output is Map(account id -> (aaSum, allAve))
    transactionsByAmount.map { case (id, amounts) =>
      id -> CalculatedStatistics(amounts.max, amounts.sum / allCounts(id), aaSums.getOrElse(id, 0.0), ccSums.getOrElse(id, 0.0), ffSums.getOrElse(id, 0.0))
    }
  }

  def getRollingTransactionById(transactions: List[Transaction], day: DayNumber): Map[AccountId, List[Transaction]] =
    transactions
      .filter(trans => trans.transactionDay >= day - 5 && trans.transactionDay < day)
      .groupBy(_.accountId)

  def sumByCategory(tsById: Map[String, List[Transaction]], category: String): Map[AccountId, Double] =
    tsById.view.mapValues(_.filter(_.category == category))
      .mapValues(_.map(_.transactionAmount).sum).toMap

  override def name: String = "question3Solution"

  override def exerciseFormatter(exerciseSolution: List[(DayNumber, Map[AccountId, CalculatedStatistics])]): String = {
    exerciseSolution.flatMap {
      case (k, v) =>
        v.map {
          case (k2, v2) => s"day: $k, accountId: $k2, maximum: ${round(v2.max)}, average: ${round(v2.average)}, aa: ${round(v2.AATotalValue)}, cc: ${round(v2.CCTotalValue)}, ff: ${round(v2.FFTotalValue)}"
        }
    }.mkString("\n")

  }


}

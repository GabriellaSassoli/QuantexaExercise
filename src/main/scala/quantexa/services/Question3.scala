package quantexa.services


import quantexa.model.{CalculatedStatistics, FinalResponse, Transaction}

import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.MapView

object Question3 extends FinalResponse[List[(Int, Map[String, CalculatedStatistics])]] {

  def getMaxDay(ts:List[Transaction]): Int= {
    ts.collect(value => value.transactionDay).max
  }


  def getRollingTransactionById(transactions: List[Transaction], day: Int): Map[String, List[Transaction]] =
    transactions
      .filter(trans => trans.transactionDay >= day - 5 && trans.transactionDay < day)
      .groupBy(_.accountId)

  def calcStats(transactions: List[Transaction], day: Int): Map[String, CalculatedStatistics] = {

  // all transactions for date range, grouped by accountID
    val transactionsById: Map[String, List[Transaction]] = getRollingTransactionById(transactions,day)

    // "AA", "CC", "FF" transactions sum by account id
    val aaSums = sumByCategory(transactionsById,"AA")
    val ccSums = sumByCategory(transactionsById, "CC")
    val ffSums = sumByCategory(transactionsById, "FF")

    // transactions by accountId
    val transactionsByAmount: Map[String, List[Double]] = transactionsById.view.mapValues(_.map(_.transactionAmount)).toMap

    // count of all transactions by account id
    val allCounts = transactionsById.view.mapValues(_.length)

    // output is Map(account id -> (aaSum, allAve))
    transactionsByAmount.map { case (id, amounts) =>
      id -> CalculatedStatistics(amounts.max,amounts.sum/allCounts(id) ,aaSums.getOrElse(id, 0.0), ccSums.getOrElse(id,0.0),ffSums.getOrElse(id, 0.0))
    }
  }

  def sumByCategory(tsById: Map[String, List[Transaction]], category: String): Map[String, Double] =
    tsById.view.mapValues(_.filter(_.category == category))
      .mapValues(_.map(_.transactionAmount).sum).toMap

  def exerciseSolver(ts: List[Transaction]): List[(Int, Map[String, CalculatedStatistics])] ={
   (0 to getMaxDay(ts)).map{ day => // is 5 correct?
      day -> calcStats(ts, day)
    }.toList
  }

  def writeFile(data:List[(Int, Map[String, CalculatedStatistics])],fileName: String) ={

    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))

    data.foreach{
      case (k,v) =>
        v.foreach{
          case (k2,v2) => bw.write(s"day: $k, accountId: $k2, maximum: ${v2.max}, average: ${v2.average}, aa: ${v2.AATotalValue}, cc: ${v2.CCTotalValue}, ff: ${v2.FFTotalValue} \n")
    }}
    bw.close()
  }


}

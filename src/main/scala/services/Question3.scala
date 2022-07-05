package services

import filesHandlers.ReadInput.getInput
import model.{CalculatedStatistics, Transaction}

import java.io.{BufferedWriter, File, FileWriter}
import scala.collection.MapView

object Question3 {

  def getMaxDay(ts:List[Transaction]): Int= {
    ts.collect(value => value.transactionDay).max
  }

  def calcStats(ts: List[Transaction], day: Int): Map[String, CalculatedStatistics] = {
  // all transactions for date range, grouped by accountID
    val tsById: Map[String, List[Transaction]] = ts
      .filter(trans => trans.transactionDay >= day - 5 && trans.transactionDay < day)
      .groupBy(_.accountId)

    // "AA", "CC", "FF" transactions sum by account id
    val aaSums = sumByCategory(tsById,"AA")
    val ccSums = sumByCategory(tsById, "CC")
    val ffSums = sumByCategory(tsById, "FF")

    // sum of all transactions by account id
    val tsByAmmount: MapView[String, List[Double]] = tsById.view.mapValues(_.map(_.transactionAmount))

    // count of all transactions by account id
    val allCounts = tsById.view.mapValues(_.length)

    // output is Map(account id -> (aaSum, allAve))
    tsByAmmount.map { case (id, amounts) =>
      id -> CalculatedStatistics(amounts.max,aaSums.getOrElse(id, 0.0), ccSums.getOrElse(id,0.0),ffSums.getOrElse(id, 0.0), amounts.sum/ allCounts(id))
    }.toMap
  }

  def sumByCategory(tsById: Map[String, List[Transaction]], category: String)=
    tsById.view.mapValues(_.filter(_.category == category))
      .mapValues(_.map(_.transactionAmount).sum)

  def exercise3Response(ts: List[Transaction]): List[(Int, Map[String, CalculatedStatistics])] ={
    (5 to getMaxDay(ts)).map{ day =>
      day -> calcStats(ts, day)
    }.toList
  }

  def writeResultex3(data:List[(Int, Map[String, CalculatedStatistics])]) ={

    val file = new File("question3")
    val bw = new BufferedWriter(new FileWriter(file))

    data.foreach{
      case (k,v) =>
        v.foreach{
          case (k2,v2) => bw.write(s"day: $k, accountId: $k2, maximum: ${v2.max}, average: ${v2.average}, aa: ${v2.AATotalValue}, cc: ${v2.CCTotalValue}, ff: ${v2.FFTotalValue} \n")
    }}
    bw.close()
  }
  

  def writeResultByRollingDays ={
    writeResultex3(exercise3Response(getInput()))
  }


}

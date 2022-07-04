package filesHandlers

import model.Transaction

import java.net.FileNameMap
import scala.io.Source

object ReadInput {

  def readInput(filename:String): List[Transaction] = {
    val transactionsLines = Source.fromResource(filename).getLines().drop(1)

    val transactions: List[Transaction] = transactionsLines.map {
      line =>
        val split = line.split(",")
        Transaction(split(0), split(1), split(2).toInt, split(3), split(4).toDouble)
    }.toList

    transactions
  }

  def getInput(fileName: String = "transaction.txt") = readInput(fileName) // is this necessary? It's to not recalculate everything

}

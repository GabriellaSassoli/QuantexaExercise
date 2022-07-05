package quantexa.filesHandlers

import quantexa.model.Transaction

import scala.io.Source
import scala.util.{Failure, Success, Try}

object ReadInput {

  def readInput(filename: String): List[Transaction] = {
    val transactionsLines = Source.fromResource(filename).getLines().drop(1)

    val transactions: List[Transaction] = transactionsLines.zipWithIndex.map {
      case (line, index) =>
        val split = line.split(",")
        Try(Transaction(split(0), split(1), split(2).toInt, split(3), split(4).toDouble)) match {
          case Success(transaction) => transaction
          case Failure(ex) => throw new Exception(s"Failed to process file $filename at line $index because of ${ex.getMessage}")
        }
    }.toList
    transactions
  }
}

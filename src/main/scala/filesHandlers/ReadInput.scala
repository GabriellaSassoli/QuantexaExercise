package filesHandlers

import model.Transaction

import java.net.FileNameMap
import scala.io.Source
import scala.reflect.runtime.universe.{Throw, Try}
import scala.util.{Failure, Success}

object ReadInput {

  def readInput(filename: String): List[Transaction] = {
    try {
      val transactionsLines = Source.fromResource(filename).getLines().drop(1)

      val transactions: List[Transaction] = transactionsLines.map {
        line =>
          val split = line.split(",")
          Transaction(split(0), split(1), split(2).toInt, split(3), split(4).toDouble)
      }.toList

      transactions

    } catch {
      case e => throw new Exception(s"Error reading file: $e")
    }
  }


}

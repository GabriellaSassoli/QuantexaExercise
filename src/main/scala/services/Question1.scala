package services

import model.{FinalResponse, Transaction}

import java.io.{BufferedWriter, File, FileWriter}

object Question1 extends FinalResponse[Map[Int, Double]] {

  override def exerciseSolver(transactions: List[Transaction]): Map[Int, Double] = {
    transactions.groupBy(_.transactionDay).map{case (day,transactions) =>
      (day, transactions.collect(_.transactionAmount).sum)
    }
  }

   override def writeFile(exerciseSolution: Map[Int, Double], fileName: String) = {

    val file = new File(fileName)

    val bw = new BufferedWriter(new FileWriter(file))

    exerciseSolution.foreach{
      case (day, totalValue)  => bw.write(s"day: $day, total value: $totalValue\n")
    }

    bw.close()
  }


}

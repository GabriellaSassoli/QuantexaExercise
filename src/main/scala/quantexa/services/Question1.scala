package quantexa.services

import quantexa.{DayNumber, TotalValue}
import quantexa.model.{FinalResponse, Transaction}

object Question1 extends FinalResponse[Map[DayNumber, TotalValue]] {

  override def exerciseSolver(transactions: List[Transaction]): Map[DayNumber, TotalValue] =
    transactions.groupMapReduce(_.transactionDay)(_.transactionAmount)(_ + _)

  override def exerciseFormatter(exerciseSolution: Map[DayNumber, TotalValue]): String =
    exerciseSolution.map{
      case (day, totalValue)  => s"day: $day, total value: $totalValue"
    }.mkString("\n")

  override def name: String = "question1Solution"
}

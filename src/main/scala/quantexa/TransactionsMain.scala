package quantexa

import quantexa.filesHandlers.ReadInput.readInput
import quantexa.model.Transaction
import quantexa.services.{Question1, Question2, Question3}

object TransactionsMain extends App {

  // If no file is read, error is thrown.
  val transactions: List[Transaction] = readInput("transaction.txt")
  List(Question1, Question2, Question3).foreach(_.solveExercise(transactions))

  println("Process Completed")
}

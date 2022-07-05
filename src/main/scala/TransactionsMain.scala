import filesHandlers.ReadInput.readInput
import model.Transaction
import services.{Question1, Question2, Question3}

object TransactionsMain extends App {

  // If no file is read, error is thrown.
  val transactions: List[Transaction] = readInput("transaction.txt")

  Question1.writeFile(Question1.exerciseSolver(transactions), "question1Solution")
  Question2.writeFile(Question2.exerciseSolver(transactions), "question2Solution")
  Question3.writeFile(Question3.exerciseSolver(transactions),"question3Solution")

  println("Process Completed")

}

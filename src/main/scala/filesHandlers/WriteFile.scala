package filesHandlers

import model.{TotalValue, FinalResponse, TransactionTypes}

import java.io._

object WriteFile {

  def writeFile[A](data: Map[A,FinalResponse], fileName: String) = {
    val file = new File(fileName)

    val bw = new BufferedWriter(new FileWriter(file))
    data.foreach{
      case (k, v: TotalValue)  => bw.write(s"day: $v, totalValue: $k\n")
      case (k,v:TransactionTypes) => bw.write(s"accountId: $k, average by transactionType: $v\n")
    }
    bw.close()
  }

}

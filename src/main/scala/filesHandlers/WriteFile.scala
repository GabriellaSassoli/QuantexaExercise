package filesHandlers

import java.io._

object WriteFile {

  def writeFile(data: Map[Int,Double],fileName: String) = {
    val file = new File(fileName)
    val bw = new BufferedWriter(new FileWriter(file))
    data.foreach{
      case (day, totalValue) => bw.write(s"day: $day, totalValue: $totalValue\n")
    }
    bw.close()
  }

}

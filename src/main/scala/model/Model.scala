package model

//import com.typesafe.scalalogging.LazyLogging
//import com.typesafe.config.{Config, ConfigFactory}
sealed trait FinalResponse{
}

case class Transaction(transactionId: String, accountId: String, transactionDay: Int, category: String, transactionAmount: Double)

case class TransactionTypes(AA: Double, BB: Double, CC: Double, DD: Double,EE: Double,FF: Double,GG: Double) extends FinalResponse

case class CalculatedStatistics(max: Double, average: Double, AATotalValue: Double, CCTotalValue: Double, FFTotalValue: Double) extends FinalResponse

case class TotalValue(totalValue:Double) extends FinalResponse
//trait AppConfig extends LazyLogging {
//  val config: Config = ConfigFactory.load
//
//  lazy val swaggerApiUrl: String = config.getString("swagger.api.url")
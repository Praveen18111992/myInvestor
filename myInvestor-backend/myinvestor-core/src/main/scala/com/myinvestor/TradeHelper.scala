package com.myinvestor

import java.util.UUID

import com.myinvestor.TradeSchema._
import org.joda.time.DateTime
import org.joda.time.format.{DateTimeFormatter, ISODateTimeFormat}
import spray.json.{JsString, _}

/**
  * Trading object.
  */
object TradeHelper {

  /**
    * Generate a type 4 UUID.
    *
    * @return UUID version 4 string.
    */
  def UUIDVersion4: UUID = java.util.UUID.randomUUID

  /**
    * Generate a UUID from string
    *
    * @param uuid UUID string
    * @return An UUID
    */
  def UUIDFromString(uuid: String): UUID = java.util.UUID.fromString(uuid)

  trait JsonApiProtocol extends DefaultJsonProtocol {

    implicit object DateTimeFormat extends RootJsonFormat[DateTime] {
      val formatter: DateTimeFormatter = ISODateTimeFormat.basicDateTimeNoMillis

      def write(obj: DateTime): JsValue = {
        JsString(formatter.print(obj))
      }

      def read(json: JsValue): DateTime = json match {
        case JsString(s) => try {
          formatter.parseDateTime(s)
        }
        catch {
          case t: Throwable => error(s)
        }
        case _ =>
          error(json.toString())
      }

      def error(v: Any): DateTime = {
        val example = formatter.print(0)
        deserializationError(f"'$v' is not a valid date value. Dates must be in compact ISO-8601 format, e.g. '$example'")
      }
    }

    implicit val batchJobFormat = jsonFormat3(BatchJob)
    implicit val exchangeFormat = jsonFormat5(Exchange)
    implicit val stockFormat = jsonFormat3(Stock)
    implicit val stockHistoryFormat = jsonFormat8(StockHistory)
    implicit val g2YFinanceMappingFormat = jsonFormat6(G2YFinanceMapping)
    implicit val stockInfoFormat = jsonFormat5(StockInfo.apply)
    implicit val stockInfo2Format = jsonFormat20(StockInfo2.apply)
    implicit val dividendSummary = jsonFormat7(DividendSummary.apply)
    implicit val dividendHistory = jsonFormat4(DividendHistory.apply)
  }

  object JsonApiSupport extends JsonApiProtocol {

  }
}

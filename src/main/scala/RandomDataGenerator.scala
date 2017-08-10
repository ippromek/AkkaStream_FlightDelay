/**
  * Created by oleg.baydakov on 25/04/2017.
  */
import akka.actor.ActorSystem
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{FileIO, Sink, Source}
import akka.util.ByteString
import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging


import scala.concurrent.Await
import scala.concurrent.duration.Duration

import java.nio.file.Paths
import java.util.UUID

import scala.util.Random

sealed trait Reading {

  def id: Int
}

case class ValidReading(id: Int, value: Double = Random.nextDouble()) extends Reading

case class InvalidReading(id: Int) extends Reading



object RandomDataGenerator {

  def main(args: Array[String]): Unit = {

    val config = ConfigFactory.load()
    val numberOfFiles = config.getInt("generator.number-of-files")
    val numberOfPairs = config.getInt("generator.number-of-pairs")
    val invalidLineProbability = config.getDouble("generator.invalid-line-probability")

    implicit val system = ActorSystem("random-data-generator")
    implicit val materializer = ActorMaterializer()

    //logger.info("Starting generation")

    val f = Source(1 to numberOfFiles)
      .mapAsyncUnordered(numberOfFiles) { _ =>
        val fileName = UUID.randomUUID().toString
        Source(1 to numberOfPairs).map { case _ =>
          val id = Random.nextInt(1000000)
          Seq(ValidReading(id), ValidReading(id)).map { reading =>
            val value = if (Random.nextDouble() > invalidLineProbability) reading.value.toString else "invalid_value"
            ByteString(s"${reading.id};$value\n")
          }.foldLeft(ByteString())(_ concat _)
        }.runWith(FileIO.toPath(Paths.get(s"C:\\Users\\oleg.baydakov\\Downloads\\2008.csv\\$fileName")))
      }
      .runWith(Sink.ignore)

    Source(1 to 100)

    Await.ready(f, Duration.Inf)
    //logger.info("Generated random data")

  }

}

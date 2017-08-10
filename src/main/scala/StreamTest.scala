import java.nio.file.Paths

import akka.actor.{Actor, ActorSystem, Props}
import akka.stream.ActorMaterializer
import akka.stream.actor.ActorPublisher
import akka.stream.actor.ActorPublisherMessage.{Cancel, Request}
import akka.stream.scaladsl.{FileIO, Flow, Framing, Keep, Sink, Source}
import akka.util.ByteString

import scala.concurrent.Promise
import scala.util.Random

/**
  * Created by oleg.baydakov on 26/04/2017.
  */
object StreamTest {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("random-data-generator")
    implicit val materializer = ActorMaterializer()

    import scala.concurrent.ExecutionContext.Implicits.global


    // https://github.com/chtefi/akka-streams-summary


    /*val source: Source[Int, Promise[Option[Int]]] = Source.maybe[Int].log("source")
    val map = Flow[Int].map(_ * 2).log("map")
    val graph = source.via(map).log("before sink").toMat(Sink.ignore)(Keep.both)
    val (promise, doneFuture) = graph.run()
    doneFuture.foreach(x => { println(s"done? $x"); system.terminate() })

    promise.success(Some(5))


    class Toto extends Actor with ActorPublisher[Char] {
      override def receive = {
        case x @ Request(n) =>  (0 until n.toInt).foreach(_ =>  {onNext(Random.nextPrintableChar())})
        case Cancel => context.stop(self)
      }
    }
    //val n=10
    Source.actorPublisher(Props(classOf[Toto])).runForeach(println)

    system.terminate() */

    // https://www.becompany.ch/en/blog/2016/07/29/realtime-log-processing
    val path_file=
    FileIO.fromPath(Paths.get("C:\\Users\\oleg.baydakov\\IdeaProjects\\AkkaStream_FlightDelay\\src\\main\\resources\\application.conf")).
     // via(delimiter(ByteString("\n"), Int.MaxValue)). // split into lines
      via(Framing.delimiter(ByteString(System.lineSeparator), Int.MaxValue, allowTruncation = true)).
      map(_.decodeString("UTF-8")). // convert ByteStrings to Strings
      map(a => {println(a);a}). // convert ByteStrings to Strings
      filter(!_.startsWith("#")). // remove comments
      fold(0)((i, _) => i + 1). // count remaining lines
      runForeach(println) // run the stream, print result

  }

}

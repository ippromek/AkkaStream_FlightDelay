import akka.actor.ActorSystem
import akka.stream._
import akka.stream.scaladsl.{Sink, Source}
import akka.stream.stage.{GraphStage, GraphStageLogic, InHandler, OutHandler}

import scala.collection.immutable




/**
  * Created by oleg.baydakov on 25/04/2017.
  */

case class Element(id: Int, value: Int)

object SampleElements {

  val E11 = Element(1, 1)
  val E21 = Element(1, 1)
  val E31 = Element(3, 1)
  val E42 = Element(4, 2)
  val E52 = Element(5, 2)
  val E63 = Element(6, 3)

  val Ones = immutable.Seq(E11, E21, E31)
  val Twos = immutable.Seq(E42, E52)
  val Threes = immutable.Seq(E63)

  val All = Ones ++ Twos ++ Threes
}

final class AccumulateWhileUnchanged[E, P](propertyExtractor: E => P)
  extends GraphStage[FlowShape[E, immutable.Seq[E]]] {

  val in = Inlet[E]("AccumulateWhileUnchanged.in")
  val out = Outlet[immutable.Seq[E]]("AccumulateWhileUnchanged.out")

  override def shape = FlowShape.of(in, out)

  override def createLogic(inheritedAttributes: Attributes) = new GraphStageLogic(shape) {

    private var currentState: Option[P] = None
    private val buffer = Vector.newBuilder[E]

    setHandlers(in, out, new InHandler with OutHandler {

      override def onPush(): Unit = {
        val nextElement = grab(in)
        val nextState = propertyExtractor(nextElement)
        println(nextState)

        if (currentState.isEmpty || currentState.contains(nextState)) {
          buffer += nextElement
          pull(in)
        } else {
          val result = buffer.result()
          buffer.clear()
          buffer += nextElement
          push(out, result)
        }

        currentState = Some(nextState)
      }

      override def onPull(): Unit = {
        pull(in)
      }

      override def onUpstreamFinish(): Unit = {
        val result = buffer.result()
        if (result.nonEmpty) {
          emit(out, result)
        }
        completeStage()
      }
    })

    override def postStop(): Unit = {
      buffer.clear()
    }
  }
}

object CustoomGraphShape {
  def main(args: Array[String]): Unit = {

    implicit val system = ActorSystem("random-data-generator")
    implicit val materializer = ActorMaterializer()


    Source(SampleElements.All)
      .via(new AccumulateWhileUnchanged(_.value))
      .runWith(Sink.foreach(println))

  }
}

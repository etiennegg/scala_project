import MsgClass.Msg
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.model._
import akka.http.scaladsl.unmarshalling.Unmarshal
import akka.stream.ActorMaterializer
import argonaut._
import Argonaut._

import scala.concurrent.duration._
import scala.concurrent.{Await, ExecutionContextExecutor, Future}
import scala.util.{Failure, Success}

object WebClient {

  val root : String = "http://localhost:8080/"

  implicit val system: ActorSystem = ActorSystem()
  implicit val materializer: ActorMaterializer = ActorMaterializer()
  // needed for the future flatMap/onComplete in the end
  implicit val executionContext: ExecutionContextExecutor = system.dispatcher

  def main(args: Array[String]): Unit = {
    MsgClass.MsgFactory
  }

  def postJson(id : String, message: Msg) : Unit = {

    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(POST, uri = root + "json/" + id))

    responseFuture
      .onComplete {
        case Success(res) => println(res)
        case Failure(_)   => sys.error("something wrong")
      }
  }


//  def requestGet(id: String = "") : Json = {
//    Parse.parse(Await.result(requestGetJson(id), 1000 millis)) match {
//      case Right(x) => x
//    }
//  }
//
//  def requestGetJson(id : String = "") : Future[String] = {
//    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(GET, uri = root + "json" + id))
//
//    responseFuture.flatMap {
//      case HttpResponse(StatusCodes.OK, _, e, _) =>
//        Unmarshal(e).to[String]
//      case HttpResponse(status, _, e, _) =>
//        e.discardBytes() //all entities should be consumed or discarded, so...
//        sys.error(status.value)
//    }
//  }

//  def putJson(id : String) : Unit = {
//    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(PUT, uri = root + "json/" + id))
//
//    responseFuture
//      .onComplete {
//        case Success(res) => println(res)
//        case Failure(_)   => sys.error("something wrong")
//      }
//  }
//
//  def deleteJson(id : String) : Unit = {
//    val responseFuture: Future[HttpResponse] = Http().singleRequest(HttpRequest(DELETE, uri = root + "json/" + id))
//
//    responseFuture
//      .onComplete {
//        case Success(res) => println(res)
//        case Failure(_)   => sys.error("something wrong")
//      }
//  }
}

import akka.actor.ActorSystem
import akka.io.IO
import akka.pattern._
import akka.util.Timeout
import spray.can.Http

import scala.concurrent.duration._
import scala.util.{Failure, Success}

object Server extends App {

  lazy val root: String =
    new java.io.File(
      if (args.length > 0) {
        args(0)
      } else {
        "."
      })
      .getCanonicalPath

  implicit lazy val system = ActorSystem("incrementor-server")
  implicit lazy val dispatcher = system.dispatcher
  implicit lazy val timeout: Timeout = Timeout(Duration(properties.server.timeout(), MILLISECONDS))

  start()

  def start(): Unit = {
    try {
      system.log.info("obtaining server actor reference...")
      val server = system.actorOf(actor.Server.props(root), "root")

      system.log.info("binding server actor reference to http...")
      val message = IO(Http) ? Http.Bind(server, interface = "localhost", port = 80)

      system.log.info("awaiting bound confirmation...")
      message
        .onComplete {
          case Success(_: Http.Bound) =>
            system.log.info("server actor bound successfully!")
            awaitTermination()

          case Failure(t: Throwable) =>
            system.log.error(t, "server encountered error during execution")
            shutdown()

          case Success(msg) =>
            system.log.warning(s"server encountered unexpected message during binding: $msg")
            shutdown()
        }


    } catch {
      case t: Throwable =>
        system.log.error(t, "server encountered error during startup")
        shutdown()
    }
  }

  def awaitTermination(): Unit = {
    system.log.info("awaiting system termination...")
    system.awaitTermination()
  }

  def shutdown(): Unit = {
    system.log.info("shutting down server...")
    awaitTermination()
  }

}

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import spray.can.Http

object Main extends App {
  implicit val system = ActorSystem()

  val handler = system.actorOf(Props[WebService], name = "handler")

  IO(Http) ! Http.Bind(handler, interface = "localhost", port = 8000)
}

import akka.actor._
import akka.util.Timeout
import spray.can.Http
import spray.http.HttpMethods._
import spray.http.MediaTypes._
import spray.http._

import scala.concurrent.duration._

class WebService extends Actor with ActorLogging {

  implicit val timeout: Timeout = 1.second

  def receive = {
    case _: Http.Connected => sender ! Http.Register(self)

    case HttpRequest(GET, Uri.Path("/"), _, _, _) =>
      sender ! index
  }

  lazy val index = HttpResponse(
    entity = HttpEntity(`text/html`, "THIS SHOULD BE A PATH BUT HOW THE FUCK")
  )

}

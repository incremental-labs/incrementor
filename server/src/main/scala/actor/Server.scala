package actor

import java.io.File

import akka.actor.{Actor, Props}
import com.typesafe.config.{Config, ConfigFactory}
import properties._
import spray.routing._

object Server {
  def props(root: String, conf: Config = ConfigFactory.load): Props = Props(classOf[Server], root, conf)
}

case class Server(root: String, conf: Config) extends HttpServiceActor with Base {

  lazy val indexFile = s"$root${File.separator}${server.index(conf)}"
  lazy val resourceDir = s"$root${File.separator}${server.resource.dir(conf)}"
  lazy val resourceRoute = s"${server.resource.route(conf)}"

  override def receive: Actor.Receive = runRoute {

    path("") {
      log.info(s"serving index from file: $indexFile")
      getFromFile(indexFile)

    } ~ pathPrefix(resourceRoute) {
      log.info(s"serving resources from directory: $resourceDir on route: $resourceRoute")
      getFromDirectory(resourceDir)

    }
  }

}
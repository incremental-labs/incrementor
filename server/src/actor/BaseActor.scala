package actor

import akka.actor.{Actor, ActorLogging}

trait BaseActor extends Actor with ActorLogging {
  implicit lazy val system = this.context.system
  implicit lazy val dispatcher = this.context.dispatcher
}

package actor

import akka.actor.{Actor, ActorLogging}

trait Base extends Actor with ActorLogging {
  implicit def system = context.system
  implicit def dispatcher = context.dispatcher
}

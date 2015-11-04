package ecs

import rx._
import ops._

trait Engine[C <: Component, T <: Tag, E <: Entity[C, T], S <: System[C, T, E]]
  extends Entities[C, T, E]
  with Systems[C, T, E, S] {

  def scheduler: Scheduler

}

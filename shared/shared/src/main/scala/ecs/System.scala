package ecs

import rx.Rx

trait System[C <: Component, T <: Tag, E <: Entity[C, T]] {

  def context: Engine[C, T, E, System[C, T, E]]

}
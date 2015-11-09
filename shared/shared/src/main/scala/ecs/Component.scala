package ecs

import cats.Later
import cats.free.Free
import simulacrum.{op, typeclass}

@typeclass trait Component[C] {
  implicit def self = this


  def create(component: () => C): Routine[C] =
    Free.liftF(
      ComponentDirective
        .Create[C, C](Later(component()), identity))

  def destroy(component: C): Routine[Unit] =
    Free.liftF(
      ComponentDirective
        .Destroy[C, Unit](component, ()))


  @op("tags", true)
  def tagSet[T: Tag](component: C): Routine[Set[T]] =
    Free.liftF(
      ComponentDirective
        .Tags[C, T, Set[T]](component, identity))

  @op("+", true)
  def addTag[T: Tag](component: C)(tag: T): Routine[C] =
    Free.liftF(
      ComponentDirective
        .Tags
        .Add[C, T, C](component, tag, identity))

  @op("-", true)
  def removeTag[T: Tag](component: C)(tag: T): Routine[C] =
    Free.liftF(
      ComponentDirective
        .Tags
        .Remove[C, T, C](component, tag, identity))

}

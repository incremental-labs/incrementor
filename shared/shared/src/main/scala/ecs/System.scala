package ecs

import cats.Later
import cats.free.Free
import simulacrum.{op, typeclass}

@typeclass trait System[S] {
  implicit def self = this

  def create(system: () => S): Routine[S] =
    Free.liftF(
      SystemDirective
        .Create[S, S](Later(system()), identity))

  def destroy(system: S): Routine[Unit] =
    Free.liftF(
      SystemDirective
        .Destroy[S, Unit](system, ()))



  @op("tags", true)
  def tagSet[T: Tag](system: S): Routine[Set[T]] =
    Free.liftF(
      SystemDirective
        .Tags[S, T, Set[T]](system, identity))

  @op("+", true)
  def addTag[T: Tag](system: S)(tag: T): Routine[S] =
    Free.liftF(
      SystemDirective
        .Tags
        .Add[S, T, S](system, tag, identity))

  @op("-", true)
  def removeTag[T: Tag](system: S)(tag: T): Routine[S] =
    Free.liftF(
      SystemDirective
        .Tags
        .Remove[S, T, S](system, tag, identity))

}

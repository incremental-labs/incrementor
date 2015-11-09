package ecs

import cats.Later
import cats.free.Free
import simulacrum.{op, typeclass}

@typeclass trait Key[K] {
  implicit def self = this

  def create(key: () => K): Routine[K] =
    Free.liftF(
      KeyDirective
        .Create[K, K](Later(key()), identity))

  def destroy(key: K): Routine[Unit] =
    Free.liftF(
      KeyDirective
        .Destroy[K, Unit](key, ()))



  @op("tags", true)
  def tagSet[T: Tag](key: K): Routine[Set[T]] =
    Free.liftF(
      KeyDirective
        .Tags[K, T, Set[T]](key, identity))

  @op("+", true)
  def addTag[T: Tag](key: K)(tag: T): Routine[K] =
    Free.liftF(
      KeyDirective
        .Tags
        .Add[K, T, K](key, tag, identity))

  @op("-", true)
  def removeTag[T: Tag](key: K)(tag: T): Routine[K] =
    Free.liftF(
      KeyDirective
        .Tags
        .Remove[K, T, K](key, tag, identity))

}

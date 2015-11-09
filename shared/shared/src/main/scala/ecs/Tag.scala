package ecs

import cats.Later
import cats.free.Free
import simulacrum.typeclass

@typeclass trait Tag[T] {
  implicit def self = this

  def create(tag: () => T): Routine[T] =
    Free.liftF(
      TagDirective
        .Create[T, T](Later(tag()), identity))

  def destroy(tag: T): Routine[Unit] =
    Free.liftF(
      TagDirective
        .Destroy[T, Unit](tag, ()))

}

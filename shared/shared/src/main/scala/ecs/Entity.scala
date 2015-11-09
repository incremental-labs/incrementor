package ecs

import cats.Later
import cats.free.Free
import simulacrum.{op, typeclass}

@typeclass trait Entity[E] {
  implicit def self = this


  def create(entity: () => E): Routine[E] =
    Free.liftF(
      EntityDirective
        .Create[E, E](Later(entity()), identity))

  def destroy(entity: E): Routine[Unit] =
    Free.liftF(
      EntityDirective
        .Destroy[E, Unit](entity, ()))



  @op("tags", true)
  def tagSet[T: Tag](entity: E): Routine[Set[T]] =
    Free.liftF(
      EntityDirective
        .Tags[E, T, Set[T]](entity, identity))

  @op("+", true)
  def addTag[T: Tag](entity: E)(tag: T): Routine[E] =
    Free.liftF(
      EntityDirective
        .Tags
        .Add[E, T, E](entity, tag, identity))

  @op("-", true)
  def removeTag[T: Tag](entity: E)(tag: T): Routine[E] =
    Free.liftF(
      EntityDirective
        .Tags
        .Remove[E, T, E](entity, tag, identity))



  @op("components", true)
  def componentMap[K: Key, C: Component](entity: E): Routine[Map[K, C]] =
    Free.liftF(
      EntityDirective
        .Components[E, K, C, Map[K, C]](entity, identity))

  @op("+", true)
  def putComponentEntry[K: Key, C: Component](entity: E)(entry: (K, C)): Routine[E] =
    Free.liftF(
      EntityDirective
        .Components
        .Put[E, K, C, E](entity, entry, identity))

  @op("-", true)
  def removeComponentEntry[K: Key, C: Component](entity: E)(entry: (K, C)): Routine[E] =
    Free.liftF(
      EntityDirective
        .Components
        .Remove[E, K, C, E](entity, entry, identity))

}

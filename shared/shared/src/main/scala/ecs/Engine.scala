package ecs

import cats.free.Free
import simulacrum.{op, typeclass}

@typeclass trait Engine[E] {
  implicit def self = this


  def components[C: Component](engine: E): Routine[Set[C]] =
    Free.liftF(
      EngineDirective
        .Components[E, C, Set[C]](engine, identity))

  @op("+", true)
  def addComponent[C: Component](engine: E)(component: C): Routine[E] =
    Free.liftF(
      EngineDirective
        .Components
        .Add[E, C, E](engine, component, identity))

  @op("-", true)
  def removeComponent[C: Component](engine: E)(component: C): Routine[E] =
    Free.liftF(
      EngineDirective
        .Components
        .Remove[E, C, E](engine, component, identity))


  def indexedComponents[K: Key, C: Component](engine: E): Routine[Map[K, C]] =
    Free.liftF(
      EngineDirective
        .Components
        .Indexed[E, K, C, Map[K, C]](engine, identity))

  @op("+", true)
  def indexComponent[K: Key, C: Component](engine: E)(entry: (K, C)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Components
        .Indexed
        .Index[E, K, C, E](engine, entry, identity))

  @op("-", true)
  def unindexComponent[K: Key, C: Component](engine: E)(entry: (K, C)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Components
        .Indexed
        .Unindex[E, K, C, E](engine, entry, identity))


  def taggedComponents[T: Tag, C: Component](engine: E): Routine[Map[T, Set[C]]] =
    Free.liftF(
      EngineDirective
        .Components
        .Tagged[E, T, C, Map[T, Set[C]]](engine, identity))

  @op("+", true)
  def tagComponent[T: Tag, C: Component](engine: E)(entry: (T, C)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Components
        .Tagged
        .Tag[E, T, C, E](engine, entry, identity))

  @op("-", true)
  def untagComponent[T: Tag, C: Component](engine: E)(entry: (T, C)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Components
        .Tagged
        .Untag[E, T, C, E](engine, entry, identity))



  def entities[En: Entity](engine: E): Routine[Set[En]] =
    Free.liftF(
      EngineDirective
        .Entities[E, En, Set[En]](engine, identity))

  @op("+", true)
  def addEntity[En: Entity](engine: E)(entity: En): Routine[E] =
    Free.liftF(
      EngineDirective
        .Entities
        .Add[E, En, E](engine, entity, identity))

  @op("-", true)
  def removeEntity[En: Entity](engine: E)(entity: En): Routine[E] =
    Free.liftF(
      EngineDirective
        .Entities
        .Remove[E, En, E](engine, entity, identity))


  def indexedEntities[K: Key, En: Entity](engine: E): Routine[Map[K, En]] =
    Free.liftF(
      EngineDirective
        .Entities
        .Indexed[E, K, En, Map[K, En]](engine, identity))

  @op("+", true)
  def indexEntity[K: Key, En: Entity](engine: E)(entry: (K, En)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Entities
        .Indexed
        .Index[E, K, En, E](engine, entry, identity))

  @op("-", true)
  def unindexEntity[K: Key, En: Entity](engine: E)(entry: (K, En)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Entities
        .Indexed
        .Unindex[E, K, En, E](engine, entry, identity))


  def taggedEntities[T: Tag, En: Entity](engine: E): Routine[Map[T, Set[En]]] =
    Free.liftF(
      EngineDirective
        .Entities
        .Tagged[E, T, En, Map[T, Set[En]]](engine, identity))

  @op("+", true)
  def tagEntity[T: Tag, En: Entity](engine: E)(entry: (T, En)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Entities
        .Tagged
        .Tag[E, T, En, E](engine, entry, identity))

  @op("-", true)
  def untagEntity[T: Tag, En: Entity](engine: E)(entry: (T, En)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Entities
        .Tagged
        .Untag[E, T, En, E](engine, entry, identity))



  def keys[K: Key](engine: E): Routine[Set[K]] =
    Free.liftF(
      EngineDirective
        .Keys[E, K, Set[K]](engine, identity))

  @op("+", true)
  def addKey[K: Key](engine: E)(key: K): Routine[E] =
    Free.liftF(
      EngineDirective
        .Keys
        .Add[E, K, E](engine, key, identity))

  @op("-", true)
  def removeKey[K: Key](engine: E)(key: K): Routine[E] =
    Free.liftF(
      EngineDirective
        .Keys
        .Remove[E, K, E](engine, key, identity))



  def taggedKeys[T: Tag, K: Key](engine: E): Routine[Map[T, Set[K]]] =
    Free.liftF(
      EngineDirective
        .Keys
        .Tagged[E, T, K, Map[T, Set[K]]](engine, identity))

  @op("+", true)
  def tagKey[T: Tag, K: Key](engine: E)(entry: (T, K)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Keys
        .Tagged
        .Tag[E, T, K, E](engine, entry, identity))

  @op("-", true)
  def untagKey[T: Tag, K: Key](engine: E)(entry: (T, K)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Keys
        .Tagged
        .Untag[E, T, K, E](engine, entry, identity))



  def systems[S: System](engine: E): Routine[Set[S]] =
    Free.liftF(
      EngineDirective
        .Systems[E, S, Set[S]](engine, identity))

  @op("+", true)
  def addSystem[S: System](engine: E)(system: S): Routine[E] =
    Free.liftF(
      EngineDirective
        .Systems
        .Add[E, S, E](engine, system, identity))

  @op("-", true)
  def removeSystem[S: System](engine: E)(system: S): Routine[E] =
    Free.liftF(
      EngineDirective
        .Systems
        .Remove[E, S, E](engine, system, identity))


  def indexedSystems[K: Key, S: System](engine: E): Routine[Map[K, S]] =
    Free.liftF(
      EngineDirective
        .Systems
        .Indexed[E, K, S, Map[K, S]](engine, identity))

  @op("+", true)
  def indexSystem[K: Key, S: System](engine: E)(entry: (K, S)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Systems
        .Indexed
        .Index[E, K, S, E](engine, entry, identity))

  @op("-", true)
  def unindexSystem[K: Key, S: System](engine: E)(entry: (K, S)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Systems
        .Indexed
        .Unindex[E, K, S, E](engine, entry, identity))


  def taggedSystems[T: Tag, S: System](engine: E): Routine[Map[T, Set[S]]] =
    Free.liftF(
      EngineDirective
        .Systems
        .Tagged[E, T, S, Map[T, Set[S]]](engine, identity))

  @op("+", true)
  def tagSystem[T: Tag, S: System](engine: E)(entry: (T, S)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Systems
        .Tagged
        .Tag[E, T, S, E](engine, entry, identity))

  @op("-", true)
  def untagSystem[T: Tag, S: System](engine: E)(entry: (T, S)): Routine[E] =
    Free.liftF(
      EngineDirective
        .Systems
        .Tagged
        .Untag[E, T, S, E](engine, entry, identity))



  def tags[T: Tag](engine: E): Routine[Set[T]] =
    Free.liftF(
      EngineDirective
        .Tags[E, T, Set[T]](engine, identity))

  @op("+", true)
  def addTag[T: Tag](engine: E)(tag: T): Routine[E] =
    Free.liftF(
      EngineDirective
        .Tags
        .Add[E, T, E](engine, tag, identity))

  @op("-", true)
  def removeTag[T: Tag](engine: E)(tag: T): Routine[E] =
    Free.liftF(
      EngineDirective
        .Tags
        .Remove[E, T, E](engine, tag, identity))


}

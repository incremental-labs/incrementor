package ecs

import rx._

trait Entities {
  this: Model =>

  lazy val entities: Rx[Set[_ <: Entity]] = Rx(declaredEntities() ++ entityStore().map(_()))
  private lazy val entityStore: Var[Set[Rx[_ <: Entity]]] = Var(Set.empty)

  def addEntity[E <: Entity](rx: Rx[E]): Entities = {
    addModel(rx)
    entityStore() += rx
    this
  }

  def removeEntity[E <: Entity](rx: Rx[E]): Entities = {
    removeModel(rx)
    entityStore() -= rx
    this
  }

  protected lazy val declaredEntities: Rx[Set[_ <: Entity]] = Rx(declaredEntityStore().map(_()))
  private lazy val declaredEntityStore: Var[Set[Var[_ <: Entity]]] = Var(Set.empty)

  protected def entity[E <: Entity](definition: => E): Var[E] = declareEntity(definition)
  private def declareEntity[E <: Entity](definition: => E): Var[E] = {
    val result = model(definition)
    declaredEntityStore() += result
    result
  }

}

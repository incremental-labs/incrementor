package ecs

import rx._

trait Systems {
  this: Model =>

  lazy val systems: Rx[Set[_ <: System]] = Rx(declaredSystems() ++ systemStore().map(_()))
  private lazy val systemStore: Var[Set[Rx[_ <: System]]] = Var(Set.empty)

  def addSystem[S <: System](rx: Rx[S]): Systems = {
    addModel(rx)
    systemStore() += rx
    this
  }

  def removeSystem[S <: System](rx: Rx[S]): Systems = {
    removeModel(rx)
    systemStore() -= rx
    this
  }

  protected lazy val declaredSystems: Rx[Set[_ <: System]] = Rx(declaredSystemStore().map(_()))
  private lazy val declaredSystemStore: Var[Set[Var[_ <: System]]] = Var(Set.empty)

  protected def system[S <: System](definition: => S): Var[S] = declareSystem(definition)
  private def declareSystem[S <: System](definition: => S): Var[S] = {
    val result = model(definition)
    declaredSystemStore() += result
    result
  }

}

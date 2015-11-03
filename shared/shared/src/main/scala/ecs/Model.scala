package ecs

import rx._

trait Model {

  lazy val models: Rx[Set[_ <: Model]] = Rx(declaredModels() ++ modelStore().map(_()))
  private lazy val modelStore: Var[Set[Rx[_ <: Model]]] = Var(Set.empty)

  def addModel[M <: Model](rx: Rx[M]): Model = {
    modelStore() += rx
    this
  }

  def removeModel[M <: Model](rx: Rx[M]): Model = {
    modelStore() -= rx
    this
  }

  protected lazy val declaredModels: Rx[Set[_ <: Model]] = Rx(declaredModelStore().map(_()))
  private lazy val declaredModelStore: Var[Set[Var[_ <: Model]]] = Var(Set.empty)

  protected def model[M <: Model](definition: => M): Var[M] = declareModel(definition)
  private def declareModel[M <: Model](definition: => M): Var[M] = {
    val result = Var(definition)
    declaredModelStore() += result
    result
  }

}

package ecs

import rx._

trait States {
  this: Model =>

  lazy val states: Rx[Set[_ <: State]] = Rx(declaredStates() ++ stateStore().map(_()))
  private lazy val stateStore: Var[Set[Rx[_ <: State]]] = Var(Set.empty)

  def addState[S <: State](rx: Rx[S]): States = {
    addModel(rx)
    stateStore() += rx
    this
  }

  def removeState[S <: State](rx: Rx[S]): States = {
    removeModel(rx)
    stateStore() -= rx
    this
  }

  protected lazy val declaredStates: Rx[Set[_ <: State]] = Rx(declaredStateStore().map(_()))
  private lazy val declaredStateStore: Var[Set[Var[_ <: State]]] = Var(Set.empty)

  protected def state[S <: State](definition: => S): Var[S] = declareState(definition)
  private def declareState[S <: State](definition: => S): Var[S] = {
    val result = model(definition)
    declaredStateStore() += result
    result
  }

}

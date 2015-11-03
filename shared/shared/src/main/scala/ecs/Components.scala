package ecs

import rx._

trait Components {
  this: Model =>

  lazy val components: Rx[Set[_ <: Component]] = Rx(declaredComponents() ++ componentStore().map(_()))
  private lazy val componentStore: Var[Set[Rx[_ <: Component]]] = Var(Set.empty)

  def addComponent[C <: Component](rx: Rx[C]): Components = {
    addModel(rx)
    componentStore() += rx
    this
  }

  def removeComponent[C <: Component](rx: Rx[C]): Components = {
    removeModel(rx)
    componentStore() -= rx
    this
  }

  protected lazy val declaredComponents: Rx[Set[_ <: Component]] = Rx(declaredComponentStore().map(_()))
  private lazy val declaredComponentStore: Var[Set[Var[_ <: Component]]] = Var(Set.empty)

  protected def component[C <: Component](definition: => C): Var[C] = declareComponent(definition)
  private def declareComponent[C <: Component](definition: => C): Var[C] = {
    val result = model(definition)
    declaredComponentStore() += result
    result
  }

}

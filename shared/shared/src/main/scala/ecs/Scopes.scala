package ecs

import rx._

trait Scopes {
  this: Model =>

  lazy val scopes: Rx[Set[_ <: Scope]] = Rx(declaredScopes() ++ scopeStore().map(_()))
  private lazy val scopeStore: Var[Set[Rx[_ <: Scope]]] = Var(Set.empty)

  def addScope[S <: Scope](rx: Rx[S]): Scopes = {
    addModel(rx)
    scopeStore() += rx
    this
  }

  def removeScope[S <: Scope](rx: Rx[S]): Scopes = {
    removeModel(rx)
    scopeStore() -= rx
    this
  }

  protected lazy val declaredScopes: Rx[Set[_ <: Scope]] = Rx(declaredScopeStore().map(_()))
  private lazy val declaredScopeStore: Var[Set[Var[_ <: Scope]]] = Var(Set.empty)

  protected def scope[S <: Scope](definition: => S): Var[S] = declareScope(definition)
  private def declareScope[S <: Scope](definition: => S): Var[S] = {
    val result = model(definition)
    declaredScopeStore() += result
    result
  }

}

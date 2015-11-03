package ecs

import rx._

trait Vars {

  lazy val vars: Rx[Set[_]] = Rx(_vars().map(_()))
  protected lazy val _vars: Var[Set[Var[_]]] = Var(Set.empty)

  protected def _var[A](definition: => A): Var[A] = {
    val result = Var(definition)
    _vars() += result
    result
  }

  protected def _var[A](definition: => A, vars: Var[Set[Var[A]]]): Var[A] = {
    val result = _var(definition)
    vars() += result
    result
  }

}

trait Components extends Vars {
  lazy val components: Rx[Set[Component]] = Rx(_components().map(_()))
  protected lazy val _components: Var[Set[Var[Component]]] = Var(Set.empty)

  protected def component(definition: => Component): Var[Component] =
    _var(definition, _components)
}

trait Entities extends Vars {
  lazy val entities: Rx[Set[Entity]] = Rx(_entities().map(_()))
  protected lazy val _entities: Var[Set[Var[Entity]]] = Var(Set.empty)

  protected def entity(definition: => Entity): Var[Entity] =
    _var(definition, _entities)
}

trait Scopes extends Vars {
  lazy val scopes: Rx[Set[Scope]] = Rx(_scopes().map(_()))
  protected lazy val _scopes: Var[Set[Var[Scope]]] = Var(Set.empty)

  protected def scope(definition: => Scope): Var[Scope] =
    _var(definition, _scopes)
}

trait Systems extends Vars {
  lazy val systems: Rx[Set[System]] = Rx(_systems().map(_()))
  protected lazy val _systems: Var[Set[Var[System]]] = Var(Set.empty)

  protected def system(definition: => System): Var[System] =
    _var(definition, _systems)
}

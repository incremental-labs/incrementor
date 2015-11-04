package ecs

import rx._
import ops._

trait Components[C <: Component] {

  def components: Rx[Set[Var[C]]] = _components

  def addComponent(component: Var[C]): Var[C] = {
    _componentToAddVar() = Some(component)
    component
  }

  def removeComponent(component: Var[C]): Var[C] = {
    _componentToRemoveVar() = Some(component)
    component
  }

  def componentToAdd: Rx[Var[C]] = _componentToAdd
  def componentToRemove: Rx[Var[C]] = _componentToRemove

  def addedComponent: Rx[Var[C]] = _addedComponent
  def removedComponent: Rx[Var[C]] = _removedComponent

  private val _components: Var[Set[Var[C]]] = Var(Set.empty, "_components")

  private val _componentToAddVar: Var[Option[Var[C]]] = Var(None, "_componentToAdd")
  protected val _componentToAdd: Rx[Var[C]] = _componentToAddVar.filter(_.isDefined).map(_.get)

  private val _addedComponentVar: Var[Option[Var[C]]] = Var(None, "_addedComponent")
  protected val _addedComponent: Rx[Var[C]] = _addedComponentVar.filter(_.isDefined).map(_.get)

  protected val _addComponent: Obs =
    _componentToAdd
      .filter(vc => !_components().contains(vc))
      .foreach(vc => {
        _components() += vc
        _addedComponentVar() = Some(vc)
      })

  private val _componentToRemoveVar: Var[Option[Var[C]]] = Var(None, "_componentToRemove")
  protected val _componentToRemove: Rx[Var[C]] = _componentToRemoveVar.filter(_.isDefined).map(_.get)

  private val _removedComponentVar: Var[Option[Var[C]]] = Var(None, "_removedComponent")
  protected val _removedComponent: Rx[Var[C]] = _removedComponentVar.filter(_.isDefined).map(_.get)

  protected val _removeComponent: Obs =
    _componentToRemove
      .filter(rxc => _components().contains(rxc))
      .foreach(rxc => {
        _components() -= rxc
        _removedComponentVar() = Some(rxc)
      })

}

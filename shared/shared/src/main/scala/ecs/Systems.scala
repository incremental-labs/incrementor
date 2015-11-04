package ecs

import rx._
import ops._

trait Systems[C <: Component, T <: Tag, E <: Entity[C, T], S <: System[C, T, E]] {

  def systems: Rx[Set[Var[S]]] = _systems

  def _addSystem(system: Var[S]): Var[S] = {
    _systemToAddVar() = Some(system)
    system
  }

  def removeSystem(system: Var[S]): Var[S] = {
    _systemToRemoveVar() = Some(system)
    system
  }

  def systemToAdd: Rx[Var[S]] = _systemToAdd
  def systemToRemove: Rx[Var[S]] = _systemToRemove

  def addedSystem: Rx[Var[S]] = _addedSystem
  def removedSystem: Rx[Var[S]] = _removedSystem

  private val _systems: Var[Set[Var[S]]] = Var(Set.empty, "_systems")

  private val _systemToAddVar: Var[Option[Var[S]]] = Var(None, "_systemToAddVar")
  protected val _systemToAdd: Rx[Var[S]] = _systemToAddVar.filter(_.isDefined).map(_.get)

  private val _addedSystemVar: Var[Option[Var[S]]] = Var(None, "_addedSystemVar")
  protected val _addedSystem: Rx[Var[S]] = _addedSystemVar.filter(_.isDefined).map(_.get)

  protected val _addSystem: Obs =
    _systemToAdd
      .filter(vc => !_systems().contains(vc))
      .foreach(vc => {
        _systems() += vc
        _addedSystemVar() = Some(vc)
      })

  private val _systemToRemoveVar: Var[Option[Var[S]]] = Var(None, "_systemToRemoveVar")
  protected val _systemToRemove: Rx[Var[S]] = _systemToRemoveVar.filter(_.isDefined).map(_.get)

  private val _removedSystemVar: Var[Option[Var[S]]] = Var(None, "_removedSystemVar")
  protected val _removedSystem: Rx[Var[S]] = _removedSystemVar.filter(_.isDefined).map(_.get)

  protected val _removeSystem: Obs =
    _systemToRemove
      .filter(rxc => _systems().contains(rxc))
      .foreach(rxc => {
        _systems() -= rxc
        _removedSystemVar() = Some(rxc)
      })

}

package ecs

import rx._
import ops._

trait Entities[C <: Component, T <: Tag, E <: Entity[C, T]] {
  
  def entities: Rx[Set[Var[E]]] = _entities

  def addEntity(entity: Var[E]): Var[E] = {
    _entityToAddVar() = Some(entity)
    entity
  }

  def removeEntity(entity: Var[E]): Var[E] = {
    _entityToRemoveVar() = Some(entity)
    entity
  }

  def entityToAdd: Rx[Var[E]] = _entityToAdd
  def entityToRemove: Rx[Var[E]] = _entityToRemove

  def addedEntity: Rx[Var[E]] = _addedEntity
  def removedEntity: Rx[Var[E]] = _removedEntity

  private val _entities: Var[Set[Var[E]]] = Var(Set.empty, "_entities")

  private val _entityToAddVar: Var[Option[Var[E]]] = Var(None, "_entityToAddVar")
  protected val _entityToAdd: Rx[Var[E]] = _entityToAddVar.filter(_.isDefined).map(_.get)

  private val _addedEntityVar: Var[Option[Var[E]]] = Var(None, "_addedEntityVar")
  protected val _addedEntity: Rx[Var[E]] = _addedEntityVar.filter(_.isDefined).map(_.get)

  protected val _addEntity: Obs =
    _entityToAdd
      .filter(vc => !_entities().contains(vc))
      .foreach(vc => {
        _entities() += vc
        _addedEntityVar() = Some(vc)
      })

  private val _entityToRemoveVar: Var[Option[Var[E]]] = Var(None, "_entityToRemoveVar")
  protected val _entityToRemove: Rx[Var[E]] = _entityToRemoveVar.filter(_.isDefined).map(_.get)

  private val _removedEntityVar: Var[Option[Var[E]]] = Var(None, "_removedEntityVar")
  protected val _removedEntity: Rx[Var[E]] = _removedEntityVar.filter(_.isDefined).map(_.get)

  protected val _removeEntity: Obs =
    _entityToRemove
      .filter(rxc => _entities().contains(rxc))
      .foreach(rxc => {
        _entities() -= rxc
        _removedEntityVar() = Some(rxc)
      })
  
}

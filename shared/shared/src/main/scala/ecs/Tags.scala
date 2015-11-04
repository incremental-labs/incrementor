package ecs

import rx._
import ops._

trait Tags[T <: Tag] {

  def tags: Rx[Set[Var[T]]] = _tags

  def addTag(tag: Var[T]): Var[T] = {
    _tagToAddVar() = Some(tag)
    tag
  }

  def removeTag(tag: Var[T]): Var[T] = {
    _tagToRemoveVar() = Some(tag)
    tag
  }

  def tagToAdd: Rx[Var[T]] = _tagToAdd
  def tagToRemove: Rx[Var[T]] = _tagToRemove

  def addedTag: Rx[Var[T]] = _addedTag
  def removedTag: Rx[Var[T]] = _removedTag

  private val _tags: Var[Set[Var[T]]] = Var(Set.empty, "_tags")

  private val _tagToAddVar: Var[Option[Var[T]]] = Var(None, "_tagToAddVar")
  protected val _tagToAdd: Rx[Var[T]] = _tagToAddVar.filter(_.isDefined).map(_.get)

  private val _addedTagVar: Var[Option[Var[T]]] = Var(None, "_addedTagVar")
  protected val _addedTag: Rx[Var[T]] = _addedTagVar.filter(_.isDefined).map(_.get)

  protected val _addTag: Obs =
    _tagToAdd
      .filter(vc => !_tags().contains(vc))
      .foreach(vc => {
        _tags() += vc
        _addedTagVar() = Some(vc)
      })

  private val _tagToRemoveVar: Var[Option[Var[T]]] = Var(None, "_tagToRemoveVar")
  protected val _tagToRemove: Rx[Var[T]] = _tagToRemoveVar.filter(_.isDefined).map(_.get)

  private val _removedTagVar: Var[Option[Var[T]]] = Var(None, "_removedTagVar")
  protected val _removedTag: Rx[Var[T]] = _removedTagVar.filter(_.isDefined).map(_.get)

  protected val _removeTag: Obs =
    _tagToRemove
      .filter(rxc => _tags().contains(rxc))
      .foreach(rxc => {
        _tags() -= rxc
        _removedTagVar() = Some(rxc)
      })

}

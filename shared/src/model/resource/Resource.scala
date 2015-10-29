package model.resource

import model.Displayed

trait Resource[+T <: ResourceType, +I <: ResourceData[T]] extends Displayed {

  def resourceType: T
  def resourceInfo: I

}

package model.resource

import model.Displayed

sealed abstract class ResourceType(val abbreviated: String) extends Displayed {
  override val display: String = s"$abbreviated resource"
  override val plural: String = display + "s"
}

case object DataResource extends ResourceType("data")
case object ProcessingResource extends ResourceType("processing")
case object PowerResource extends ResourceType("power")
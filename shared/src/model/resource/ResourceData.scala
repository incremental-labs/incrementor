package model.resource

import model.Displayed

sealed abstract class ResourceData[+T <: ResourceType](val resourceType: T,
                                                       val abbreviated: String)
  extends Displayed {

  override val display: String = s"$abbreviated resource data"
  override val plural: String = display

}

case object DataResourceData extends ResourceData(DataResource, "data")
case object PowerResourceData extends ResourceData(PowerResource, "power")
case object ProcessingResourceData extends ResourceData(ProcessingResource, "processing")
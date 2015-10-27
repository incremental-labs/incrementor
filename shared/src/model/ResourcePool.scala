package model

case class ResourcePool(resource: Resource, count: Long) {
  def name = s"$resource Stored"
  override def toString: String =  s"$name: $count ${resource.units(count)}"

}

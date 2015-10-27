package model

case class User(name: String, resources: Set[ResourcePool] = Set.empty)

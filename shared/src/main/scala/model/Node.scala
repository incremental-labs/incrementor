package model

sealed trait Node {
  def isEmpty: Boolean
}

case object EmptyNode extends Node {
  val isEmpty = true
}

sealed trait NonEmptyNode extends Node {
  val isEmpty = false
}
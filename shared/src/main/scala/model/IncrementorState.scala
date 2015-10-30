package model

trait IncrementorState[N <: NonEmptyNode] {
  def nodes: NodeMap[N]
}

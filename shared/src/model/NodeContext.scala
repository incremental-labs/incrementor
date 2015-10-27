package model

trait NodeContext[+R <: Resource, +S <: Store[R], +N <: Node[R, S]] {
  def grid: Map[(Int, Int), N]
  def neighbors(position: (Int, Int)): Seq[Option[N]] =
    position.neighbors.map(grid.get)
}
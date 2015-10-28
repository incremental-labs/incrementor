package model.mutable

trait NodeContext[+R <: Resource, +S <: Store[R], N <: Node[R, S]] extends model.NodeContext[R, S, N] {
   var grid: Map[(Int, Int), N]
   override def neighbors(position: (Int, Int)): Seq[Option[N]] = position.neighbors.map(grid.get)
 }

package model.immutable

trait NodeContext[+R <: model.Resource, +S <: model.Store[R], +N <: model.Node[R, S]] extends model.NodeContext[R, S, N] {
   val grid: Map[(Int, Int), N]
   override def neighbors(position: (Int, Int)): Seq[Option[N]] = position.neighbors.map(grid.get)
 }

package object model {

  type Position = (Int, Int)
  object Position {
    val neighbors: Seq[Position] = Seq((1, 0), (1, -1), (0, -1), (-1, 0), (-1, 1), (0, 1))
    val diagonals: Seq[Position] = Seq((2, -1), (1, -2), (-1, -1), (-2, 1), (-1, 2), (1, 1))
  }

  type NodeMap[N <: NonEmptyNode] = Map[Position, N]

  implicit class PimpedNodeMap[N <: NonEmptyNode](val self: NodeMap[N]) extends AnyVal {

    def node(position: Position): Node = self.getOrElse(position, EmptyNode)

    def neighbors(position: Position): Seq[Node] = position.neighbors.map(this.node)
    def diagonals(position: Position): Seq[Node] = position.diagonals.map(this.node)

  }

  implicit class PimpedPosition(val self: Position) extends AnyVal {

    def q = self._1
    def r = self._2

    def unary_-() = (-this.q, -this.r)

    def +(that: Position) = (this.q + that.q, this.r + that.r)
    def -(that: Position) = (this.q - that.q, this.r - that.r)

    def +(that: Int) = (this.q + that, this.r + that)
    def -(that: Int) = (this.q - that, this.r - that)
    def *(that: Int) = (this.q * that, this.r * that)
    def /(that: Int) = (this.q / that, this.r / that)
    def %(that: Int) = (this.q % that, this.r % that)

    def neighbors: Seq[Position] = Position.neighbors.map(this.+)
    def diagonals: Seq[Position] = Position.diagonals.map(this.+)

  }

  implicit class PositionPimpedInt(val self: Int) extends AnyVal {

    def +(that: Position) = (self + that.q, self + that.r)
    def -(that: Position) = (self - that.q, self - that.r)
    def *(that: Position) = (self * that.q, self * that.r)
    def /(that: Position) = (self / that.q, self / that.r)
    def %(that: Position) = (self % that.q, self % that.r)

  }

}

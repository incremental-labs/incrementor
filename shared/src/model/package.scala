
package object model {
  val neighbors = Seq((1, 0), (1, -1), (0, -1), (-1, 0), (-1, 1), (0, 1))
  implicit class Position(val self: (Int, Int)) extends AnyVal {
    def q = self._1
    def r = self._2
    def unary_-() = (-this.q, -this.r)
    def +(that: Position) = (this.q + that.q, this.r + that.r)
    def -(that: Position) =  this + (- that)
    def neighbors = model.neighbors.map(_ + this)
  }
}

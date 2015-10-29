
package object model {
  val neighbors = Seq((1, 0), (1, -1), (0, -1), (-1, 0), (-1, 1), (0, 1))
  val diagonals = Seq((2, -1), (1, -2), (-1, -1), (-2, 1), (-1, 2), (1, 1))

  implicit class PositionPimpedInt(val self: Int) extends AnyVal {

    def +(that: (Int, Int)) = that + self
    def -(that: (Int, Int)) = (- that) + self
    def *(that: (Int, Int)) = that * self
    def /(that: (Int, Int)) = (self / that.q, self / that.q)
    def %(that: (Int, Int)) = (self % that.q, self % that.q)

  }

  implicit class Position(val self: (Int, Int)) extends AnyVal {

    def q = self._1
    def r = self._2

    def unary_-() = (-this.q, -this.r)

    def +(that: Position) = (this.q + that.q, this.r + that.r)
    def +(that: Int) = (this.q + that, this.r + that)
    def -(that: Position) =  this + (- that)
    def -(that: Int) = this + (- that)
    def *(that: Int) = (this.q * that, this.r * that)
    def /(that: Int) = (this.q / that, this.r / that)
    def %(that: Int) = (this.q % that, this.r % that)

    def map[U](f: Int => U) = (f(this.q), f(this.r))

    def neighbors = model.neighbors.map(_ + this)
    def diagonals = model.diagonals.map(_ + this)

  }

  def abs(x: BigInt): BigInt = if(x < 0) -x else x
  def abs(x: BigDecimal): BigDecimal = if(x < 0) -x else x

}


package object util {

  implicit class RichString(val self: String) extends AnyVal {
    def splitCamelCase: Seq[String] = {
      def helper(source: Seq[Char], split: Seq[Seq[Char]]): Seq[String] = {
        (source, split) match {
          case (Nil, _) =>
            split.map(_.mkString)
          case (x :: xs, Nil) =>
            helper(xs, (x :: Nil) :: Nil)
          case (x :: xs, _) if x.isUpper && split.last.forall(_.isUpper) =>
            helper(xs, split.init :+ (split.last :+ x))
          case (x :: xs, _) if x.isUpper =>
            helper(xs, split :+ Seq(x))
          case (x :: xs, _) if x.isLower && split.last.size > 1 && split.last.forall(_.isUpper) =>
            helper(xs, split.init :+ split.last.init :+ Seq(split.last.last, x))
          case (x :: xs, _) if x.isLower =>
            helper(xs, split.init :+ (split.last :+ x))
        }
      }
      helper(self.toList, Nil)
    }
  }

  implicit class RichProduct[P <: AnyRef with Product](val self: P) extends AnyVal {
    def prettyProductPrefix = self.productPrefix.splitCamelCase.mkString(" ")
  }

  implicit class RichBigInt(val self: BigInt) extends AnyVal {
    def toBigDecimal: BigDecimal = BigDecimal(self)
  }

  implicit def t2option[T](t: T): Option[T] = Option(t)
  implicit def biginit2bigdecimal(i: BigInt): BigDecimal = i.toBigDecimal

  def pow(x: BigInt, y: BigInt): BigInt = {
    def helper(a: BigInt, b: BigInt, acc: BigInt = 1): BigInt = {
      b match {
        case exp if exp == BigInt(0) => acc
        case exp if exp < 0 => helper(a, b + 1, acc * a)
        case exp if exp > 0 => helper(a, b - 1, acc * a)
      }
    }
    helper(x, y)
  }

}

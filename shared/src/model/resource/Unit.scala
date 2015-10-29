package model.resource

import model._
import util._

sealed abstract class Unit(val display: String,
                           val abbreviated: String,
                           val plural: String)
  extends Displayed {

  def prefix(count: BigInt): (BigDecimal, String, String) = count match {
    case c if c >= pow(10, 24)                    => (c.toBigDecimal / pow(10, 24), "Yotta", "Y")
    case c if c >= pow(10, 21) && c < pow(10, 24) => (c.toBigDecimal / pow(10, 21), "Zetta", "Z")
    case c if c >= pow(10, 18) && c < pow(10, 21) => (c.toBigDecimal / pow(10, 18), "Exa",   "E")
    case c if c >= pow(10, 15) && c < pow(10, 18) => (c.toBigDecimal / pow(10, 15), "Peta",  "P")
    case c if c >= pow(10, 12) && c < pow(10, 15) => (c.toBigDecimal / pow(10, 12), "Tera",  "T")
    case c if c >= pow(10, 9)  && c < pow(10, 12) => (c.toBigDecimal / pow(10, 9),  "Giga",  "G")
    case c if c >= pow(10, 6)  && c < pow(10, 9)  => (c.toBigDecimal / pow(10, 6),  "Mega",  "M")
    case c if c >= pow(10, 3)  && c < pow(10, 6)  => (c.toBigDecimal / pow(10, 3),  "kilo",  "k")
    case c if c >= 0           && c < pow(10, 3)  => (c.toBigDecimal,               "",      "")
    case c if c <  0                              => val (n, pre, abb) = prefix(-c); (-n, pre, abb)
  }


  def units(count: BigInt): String = {
    val (n, pre, _) = prefix(count)
    if (abs(n) == BigDecimal(1.0)) s"$n ${pre + display}" else s"$n ${pre + plural}"

  }

  def abbreviation(count: BigInt): String = {
    val (n, _, abb) = prefix(count)
    s"$n ${abb + abbreviated}"
  }

}

case object Bit extends Unit("bit", "b", "bits")
case object Hertz extends Unit("Hertz", "Hz", "Hertz")
case object Watt extends Unit("Watt", "W", "Watts")

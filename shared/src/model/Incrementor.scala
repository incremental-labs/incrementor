package model

import scala.concurrent.duration.Duration

trait Incrementor[R <: Resource] extends Resource {
  this: Product =>
  val resource: R
  override def name = s"${resource.name} $productPrefix"
  def apply(count: Long): Long
}

trait Triggered[R <: Resource, T <: Trigger]  {
  this: Incrementor[R] =>
  val trigger: T
}

trait Interval[R <: Resource] {
  this: Incrementor[R] =>
  val interval: Duration
}

case object PSU extends Incrementor[Power.type] with Triggered[Power.type, Click.type] {
  val resource = Power
  val trigger = Click
  def apply(count: Long): Long = count + 1
  def units(count: Long): String = count match {
    case 1 => "PSU"
    case n if n > 1 => "PSUs"
  }
}

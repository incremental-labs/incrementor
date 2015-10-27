package model

trait Trigger {
  this: Product =>
  def name: String = this.productPrefix
}

case object Click extends Trigger

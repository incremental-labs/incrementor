package model

trait Resource {
  this: Product =>

  def name: String = this.productPrefix
  def units(count: Long): String
  override def toString: String = name

}

case object Power extends Resource {
  def units(count: Long): String = "A"
}

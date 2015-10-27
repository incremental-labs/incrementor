package model

trait Resource {

  def name: String
  def units(count: Long): String

}

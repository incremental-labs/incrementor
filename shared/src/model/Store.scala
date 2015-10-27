package model

trait Store[+R <: Resource] {

  def resource: R
  def count: Long

}

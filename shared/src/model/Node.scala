package model

trait Node[+R <: Resource, +S <: Store[R]] {

  def name: String
  def pos: (Int, Int)
  def resource: R
  def store: S

}
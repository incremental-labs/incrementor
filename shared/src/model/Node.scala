package model

trait Node[+R <: Resource, +S <: Store[R]] {

  val name: String
  val pos: (Int, Int)
  val resource: R
  val store: S

}
package model.immutable

trait Node[+R <: model.Resource, +S <: model.Store[R]] extends model.Node[R, S] {

  val name: String
  val pos: (Int, Int)
  val resource: R
  val store: S

}
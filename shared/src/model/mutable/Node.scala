package model.mutable

trait Node[R <: model.Resource, S <: model.Store[R]] extends model.Node[R, S] {

  var name: String
  var pos: (Int, Int)
  var resource: R
  var store: S

}
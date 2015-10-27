package model.mutable

trait Incrementor[R <: model.Resource, +S <: model.Store[R]] extends model.Incrementor[R, S] {

  var resource: R

}

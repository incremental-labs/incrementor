package model.immutable

trait Incrementor[+R <: model.Resource, +S <: model.Store[R]] extends model.Incrementor[R, S] {

  val resource: R

}

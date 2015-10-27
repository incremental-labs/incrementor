package model.mutable

trait Store[R <: model.Resource] extends model.Store[R] {

  var resource: R
  var count: Long

}

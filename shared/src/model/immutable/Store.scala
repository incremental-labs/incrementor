package model.immutable

trait Store[+R <: model.Resource] extends model.Store[R] {

  val resource: R
  val count: Long

}

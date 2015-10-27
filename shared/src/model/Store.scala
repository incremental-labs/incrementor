package model

trait Store[+R <: Resource] {

  val resource: R
  val count: Long

}

package model

trait Incrementor[+R <: Resource, +S <: Store[R]] extends Resource {

  def resource: R
  def apply[S1 >: S](store: S1): S

}

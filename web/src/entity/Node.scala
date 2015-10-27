package entity;

import scala.scalajs.js

trait Node {
  val nodeType: String
  val inc: Int

  def render: Unit
  def increment: Unit
}

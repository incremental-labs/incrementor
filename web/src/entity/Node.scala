package entity;

import scala.scalajs.js
import org.scalajs.dom

trait Node {
  val nodeType: String
  val inc: Int

  def render: Unit
  def increment: Unit
}

package entity;

import scala.scalajs.js
import org.scalajs.dom

trait Node {
  val nodeType: String
  val inc: Int

  def render(ctx:dom.CanvasRenderingContext2D, x: Int, y: Int, width: Int, height: Int): Unit
  def increment: Unit
}

package entity;

import scala.scalajs.js
import js.annotation._
import org.scalajs.dom
import phaser._

trait Node {
  val nodeType: String
  val inc: Int
  var sprite: Sprite

  def increment: Unit
  def init(x: Double, y: Double, key: String): Unit
  def update: Unit
}

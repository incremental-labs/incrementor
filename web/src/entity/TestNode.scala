package entity;

import scala.scalajs.js
import js.Dynamic.{ global => g }
import org.scalajs.dom.ext._
import org.scalajs.dom
import dom.html

class TestNode extends Node {
  val nodeType: String = "TestNode"
  val inc: Int = 3
  var total: Int = 0

  def increment = {
    total += inc
  }

  def render: Unit = ???
  def init: Unit = ???

}

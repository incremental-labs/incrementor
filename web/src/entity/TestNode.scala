package entity;

import org.scalajs.jquery.jQuery

class TestNode extends Node {
  val nodeType: String = "TestNode"
  val inc: Int = 3
  var total: Int = 0

  def increment(): Unit = {
    total += inc
    jQuery("#total").text(this.total.toString)
  }

  def render(): Unit = {
    jQuery("body").append("<div class='testnode'></div>")
    jQuery(".testnode").append("<p>TestNode</p>")
    jQuery(".testnode").append("<p id='total'>" + this.total + "</p>")
    jQuery(".testnode").click(increment _)
  }
}

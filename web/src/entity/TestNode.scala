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

  def render(ctx: dom.CanvasRenderingContext2D, x: Int, y: Int, width: Int, height: Int): Unit = {
    ctx.strokeStyle = "black"
    ctx.strokeRect(x, y, width, height)
    ctx.fillStyle = "black"
    ctx.font = "14pt sans-serif"
    ctx.fillText(nodeType, x + 30, y + 60)
    ctx.fillText(total.toString, x + 60, y + 100)
  }

  def init(ctx: dom.CanvasRenderingContext2D, canvas: html.Canvas): Unit = {
    val width = 150
    val height = 150
    val x = canvas.width / 2 - width
    val y = canvas.height / 2 - height

    render(ctx, x, y, width, height)

    canvas.onclick = (e: dom.MouseEvent) => {
      val posX = e.clientX
      val posY = e.clientY

      if (posX > x && posX < x + width && posY > y && posY < y + height) {
        increment
        ctx.clearRect(0, 0, canvas.width, canvas.height)
        render(ctx, x, y, width, height)
      }
    }
  }

}

import scala.scalajs.js
import scala.scalajs.js.JSApp
import org.scalajs.dom.ext._
import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
import entity._

@JSExport
object Main {

  val testNode = new TestNode()

  @JSExport
  def main(canvas: html.Canvas): Unit = {
    initGame(canvas)
  }

  def initGame(canvas: html.Canvas) {
    val ctx = canvas.getContext("2d").cast[dom.CanvasRenderingContext2D]

    canvas.width = canvas.parentElement.clientWidth
    canvas.height = canvas.parentElement.clientHeight

    testNode.init(ctx, canvas)
  }

}

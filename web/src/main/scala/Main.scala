import scala.scalajs.js
import org.scalajs.dom
import dom.html
import org.scalajs.jquery.jQuery
import js.annotation._
import lib._

@JSExport
object Main {

  @JSExport
  def main(canvas: html.Canvas): Unit = {
    val game = new Game(0, 0, 800, 600)

    val psu = new PSU(0, 0)
  }

}

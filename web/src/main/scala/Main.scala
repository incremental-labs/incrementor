import lib._
import org.scalajs.dom
import org.scalajs.dom.html

import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation._

@JSExport
object Main extends JSApp {

  @JSExport
  def main(): Unit = {
    val game = new Game(0, 0, 800, 600)
    val psu = new PSU(0, 0)
  }

}

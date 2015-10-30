import scala.scalajs.js
import scala.scalajs.js.JSApp
import js.Dynamic.{ global => g }
import org.scalajs.dom.ext._
import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
import phaser._
import entity._

@JSExport
object Main {

  @JSExport
  def main(): Unit = {
    g.game = new Game(width = dom.window.innerWidth,
                        height = dom.window.innerHeight,
                        parent = "",
                        state = js.Dynamic.literal(preload = preload, create = create))

  }

  val preload = () => g.game.load.image("ideos", "assets/IDEOS.png", false)

  val create = () => g.game.add.sprite(700, 300, "ideos")

}

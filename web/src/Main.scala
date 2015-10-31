import scala.scalajs.js
import scala.scalajs.js.JSApp
import js.Dynamic.{ global => g }
import org.scalajs.dom.ext._
import org.scalajs.dom
import dom.html
import scalajs.js.annotation.JSExport
import phaser._

@JSExport
object Main {

  @JSExport
  def main(): Unit = {
    g.game = new Game(width = dom.window.innerWidth,
                      height = dom.window.innerHeight,
                      parent = "",
                      state = js.Dynamic.literal(preload = preload, create = create, update = update))
  }

  val preload = () => {
    g.game.stage.backgroundColor = "#666"
    g.game.load.image("ideos", "assets/hex.png", false)
  }

  val create = () => {
    g.game.world.setBounds(0, 0, 2000, 2000)

    g.game.add.sprite(650, 300, "ideos")
  }

  val update = () => {    
    if (g.game.input.activePointer.isDown) {
      if (g.game.origDragPoint) {
        g.game.camera.x += g.game.origDragPoint.x - g.game.input.activePointer.position.x
        g.game.camera.y += g.game.origDragPoint.y - g.game.input.activePointer.position.y
      }
      g.game.origDragPoint = g.game.input.activePointer.position.clone()
    } else {
      g.game.origDragPoint = null
    }
  }

}

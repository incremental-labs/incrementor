import scala.scalajs.js
import js.Dynamic.{ global => g }
import org.scalajs.dom
import scalajs.js.annotation.JSExport
import phaser._

@JSExport
object Main {

  var game: Game = null
  var camOrig: Point = null

  @JSExport
  def main(): Unit = {
    game = new Game(width = dom.window.innerWidth,
                      height = dom.window.innerHeight,
                      parent = "",
                      state = js.Dynamic.literal(preload = preload, create = create, update = update))
  }

  val preload = () => {
    game.stage.backgroundColor = "#666"
    game.load.image("hex", "assets/hex.png", false)
    game.load.image("hex2", "assets/hex.png", false)
  }

  val create = () => {
    game.world.setBounds(0, 0, 10000, 10000)

    game.add.sprite(650, 300, "hex")
    game.add.sprite(0, 0, "hex2")
  }

  val update = () => {
    dragCam(game.input.mousePointer)
  }

  def dragCam(mousePointer: Pointer): Unit = {
    if (mousePointer.isDown) {
      if (camOrig != null) {
        game.camera.x += camOrig.x - mousePointer.position.x
        game.camera.y += camOrig.y - mousePointer.position.y
      }

      camOrig = mousePointer.position.clone()
    }

    if (mousePointer.isUp) camOrig = null
  }

}

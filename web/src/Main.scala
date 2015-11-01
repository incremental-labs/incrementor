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

  }

  val create = () => {
    game.world.setBounds(0, 0, 10000, 10000)
    game.camera.x = game.world.width / 2;
    game.camera.y = game.world.height / 2;

    val hex = game.add.sprite(0, 0, "hex")
    hex.x = (game.camera.x + game.camera.width / 2) - hex.width / 2
    hex.y = (game.camera.y + game.camera.height / 2) - hex.height / 2

    val hex2 = game.add.sprite(0, 0, "hex")
    hex2.x = hex.x - hex.width / 2 - 75
    hex2.y = hex.y - hex.height / 2 + 20

    val hex3 = game.add.sprite(0, 0, "hex")
    hex3.x = hex2.x
    hex3.y = hex2.y + hex2.height - 40

    val hex4 = game.add.sprite(0, 0, "hex")
    hex4.x = hex.x
    hex4.y = hex3.y + hex3.height / 2 - 20

    val hex5 = game.add.sprite(0, 0, "hex")
    hex5.x = hex.x + hex.width / 2 + 75
    hex5.y = hex3.y

    val hex6 = game.add.sprite(0, 0, "hex")
    hex6.x = hex.x + hex.width / 2 + 75
    hex6.y = hex2.y

    val hex7 = game.add.sprite(0, 0, "hex")
    hex7.x = hex.x
    hex7.y = hex6.y - hex6.height / 2 + 20
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

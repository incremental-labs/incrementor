package phaser

import scala.scalajs.js
import js.annotation._
import js.|
import org.scalajs.dom.html

@js.native
object Phaser extends js.Object {
  val AUTO: Int = js.native
  val CANVAS: Int = js.native
}

@js.native
@JSName("Phaser.Game")
class Game(
  width: Double | String = 800,
  height: Double | String = 600,
  renderer: Int = Phaser.AUTO,
  parent: String | html.Element = "",
  state: js.Dynamic) extends js.Object {

  val add: GameObjectFactory = js.native
  val load: Loader = js.native
}

@js.native
@JSName("Phaser.Loader")
class Loader(game: Game) extends js.Object {
  def image(key: String, url: String, overwrite: Boolean): Loader = js.native
}

@js.native
@JSName("Phaser.GameObjectFactory")
class GameObjectFactory(game: Game) extends js.Object {
  def sprite(x: Double = 0, y: Double = 0, key: String = js.native): Sprite = js.native
}

@js.native
@JSName("Phaser.Sprite")
class Sprite(game: Game, x: Double = 0, y: Double = 0, key: String, frame: String) extends js.Object

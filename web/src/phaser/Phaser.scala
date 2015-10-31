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
  state: js.Dynamic = null) extends js.Object {

  var state: State = js.native

  val add: GameObjectFactory = js.native
  val load: Loader = js.native
  var stage: Stage = js.native
  val world: World = js.native
  val camera: Camera = js.native
  val input: Input = js.native
}

@js.native
@JSName("Phaser.StateManager")
class StateManager(game: Game, pendingState: State) extends js.Object

@js.native
@JSName("Phaser.State")
abstract class State() extends js.Object {
  protected final def game: Game = js.native
  protected final def load: Loader = js.native

  def preload: Unit = js.native
  def create: Unit = js.native
  def update: Unit = js.native
}

@js.native
@JSName("Phaser.Stage")
class Stage(game: Game) extends js.Object {
  var backgroundColor: Double | String =  js.native
}

@js.native
@JSName("Phaser.World")
class World(game: Game) extends js.Object {
  def setBounds(x: Double, y: Double, width: Double, height: Double): Unit = js.native
}

@js.native
@JSName("Phaser.Camera")
class Camera(game: Game, id: Double, x: Double, y: Double, width: Double, height: Double) extends js.Object {
  var x: Double = js.native
  var y: Double = js.native
}

@js.native
@JSName("Phaser.Input")
class Input(game: Game) extends js.Object {
  var activePointer: Pointer = js.native
  var mousePointer: Pointer = js.native
}

@js.native
@JSName("Phaser.Pointer")
class Pointer(game: Game, id: Double, pointerMode: PointerMode) extends js.Object {
  val position: Point = js.native
  var isUp: Boolean = js.native
  var isDown: Boolean = js.native
}

@js.native
@JSName("Phaser.Point")
class Point(x: Double, y: Double) extends js.Object {
  var x: Double = js.native
  var y: Double = js.native
  def clone(outPut: Point = null): Point = js.native
}

@js.native
@JSName("Phaser.PointerMode")
class PointerMode extends js.Object

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

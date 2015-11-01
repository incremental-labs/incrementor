package entity;

import scala.scalajs.js
import js.annotation._
import phaser._

class TestNode(game: Game) extends Node {
  val nodeType: String = "TestNode"
  val inc: Int = 3
  var total: Int = 0
  var sprite: Sprite = null
  var text: Text = null

  def increment = {
    total += inc
  }

  def init(x: Double, y: Double, key: String): Unit = {
    sprite = game.add.sprite(x, y, key)
    text = game.add.text(sprite.x + sprite.width / 2 - 20, sprite.y + sprite.height / 2 - 10, total.toString)
    sprite.addChild(text)
    sprite.inputEnabled = true
    sprite.events.onInputDown.add((sprite: Sprite) => increment)
  }

  def update(): Unit = {
    text.text = total.toString
  }

}

package lib

import org.scalajs.dom
import org.scalajs.dom._

trait GameObject {
  def x: Double
  def y: Double
}

case class Sprite(x: Double, y: Double, path: String = "") extends GameObject

trait Hex extends GameObject {
  val hexType: String
  val sprite = new Sprite(x, y, "resources/assets/img/hex.png")
  val socket = new dom.WebSocket("ws://localhost:8000/" + hexType.toLowerCase)

  socket.onopen = (x: Event) => socket.send("New " + hexType.toLowerCase + " created.")
  socket.onmessage = (x: MessageEvent) => println("You are connected.")
}

case class PSU(x: Double, y: Double, hexType: String = "PSU") extends Hex
case class CPU(x: Double, y: Double, hexType: String = "CPU") extends Hex
case class RAM(x: Double, y: Double, hexType: String = "RAM") extends Hex
case class HDD(x: Double, y: Double, hexType: String = "HDD") extends Hex

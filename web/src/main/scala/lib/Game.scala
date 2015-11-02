package lib

import scala.scalajs.js
import org.scalajs.dom
import dom.html
import org.scalajs.jquery.jQuery

class Game(x: Double, y: Double, width: Double, height: Double) {

  val canvas = dom.document.createElement("canvas").asInstanceOf[dom.raw.HTMLCanvasElement]
  val gl = canvas.getContext("webgl")
  val init = {
    canvas.id = "canvas"
    canvas.style.display = "block"
    dom.document.body.appendChild(canvas)

    gl.viewport(x, y, width, height)
    gl.clearColor(0.0, 0.0, 0.0, 1.0)
    gl.enable(gl.DEPTH_TEST)
    gl.depthFunc(gl.LEQUAL)
    gl.clear(gl.COLOR_BUFFER_BIT | gl.DEPTH_BUFFER_BIT)
  }

  // def add(gameObject: GameObject): GameObject = {
  //
  // }

}

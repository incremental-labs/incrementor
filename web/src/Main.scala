import scala.scalajs.js.JSApp
import scala.scalajs.js.annotation.JSExport
import org.scalajs.jquery.jQuery
import entity.TestNode

object Main extends JSApp {

  val testNode = new TestNode()

  def main(): Unit = {
    testNode.render()
  }

}

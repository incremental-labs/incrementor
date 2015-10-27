package model

trait Resource {

  val name: String

  def units(count: Long): String = count match {
    case n if n == 1 || n == -1 => name
    case _ => s"${name}s"
  }
}

package ecs

import rx._
import ops._

trait Entity[C <: Component, T <: Tag]
  extends Components[C]
  with Tags[T] {


}

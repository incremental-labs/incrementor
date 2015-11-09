import cats.free.Free

package object ecs {

  type Routine[A] = Free[Directive, A]

}

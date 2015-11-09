package ecs.std

import ecs.Component

trait ComponentInstances {
  implicit def component[C] = new Component[C] {}
}

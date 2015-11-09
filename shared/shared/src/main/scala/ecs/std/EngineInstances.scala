package ecs.std

import ecs.Engine

trait EngineInstances {
  implicit def engine[E] = new Engine[E] {}
}

package ecs.std

import ecs.Tag

trait TagInstances {
  implicit def tag[T] = new Tag[T] {}
}

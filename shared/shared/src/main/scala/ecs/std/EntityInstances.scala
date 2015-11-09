package ecs.std

import ecs.Entity

trait EntityInstances {
  implicit def entity[E] = new Entity[E] {}
}

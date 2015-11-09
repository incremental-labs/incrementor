package ecs.std

import ecs.Key

trait KeyInstances {
  implicit def key[K] = new Key[K] {}
}

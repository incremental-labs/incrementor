package ecs.std

import ecs.System

trait SystemInstances {
  implicit def system[S] = new System[S] {}
}

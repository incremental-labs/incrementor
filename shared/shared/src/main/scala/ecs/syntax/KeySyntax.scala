package ecs.syntax

import ecs.Key

trait KeySyntax {
  implicit def allKeyOps[K: Key](entity: K): Key.AllOps[K] =
    Key.ops.toAllKeyOps(entity)
}

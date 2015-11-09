package ecs.syntax

import ecs.Entity

trait EntitySyntax {
  implicit def allEntityOps[E: Entity](entity: E): Entity.AllOps[E] =
    Entity.ops.toAllEntityOps(entity)
}

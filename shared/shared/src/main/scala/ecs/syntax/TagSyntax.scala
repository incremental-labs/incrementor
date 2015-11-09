package ecs.syntax

import ecs.Tag

trait TagSyntax {
  implicit def allTagOps[T: Tag](tag: T): Tag.AllOps[T] =
    Tag.ops.toAllTagOps(tag)
}

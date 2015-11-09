package ecs.syntax

import ecs.Component

trait ComponentSyntax {
  implicit def allComponentOps[C: Component](component: C): Component.AllOps[C] =
    Component.ops.toAllComponentOps(component)
}

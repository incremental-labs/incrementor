package ecs.syntax

import ecs.System

trait SystemSyntax {
  implicit def allSystemOps[S: System](system: S): System.AllOps[S] =
    System.ops.toAllSystemOps(system)
}

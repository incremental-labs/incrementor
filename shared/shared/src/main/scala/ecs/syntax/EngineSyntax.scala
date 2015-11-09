package ecs.syntax

import ecs.Engine

trait EngineSyntax {
  implicit def allEngineOps[E: Engine](engine: E): Engine.AllOps[E] =
    Engine.ops.toAllEngineOps(engine)
}

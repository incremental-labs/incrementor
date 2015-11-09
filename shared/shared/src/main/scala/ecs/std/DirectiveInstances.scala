package ecs.std

import cats.Functor
import ecs._

trait DirectiveInstances {
  implicit def directiveFunctor = DirectiveFunctor
}

private[std] object DirectiveFunctor extends Functor[Directive] {
  override def map[A, B](fa: Directive[A])(f: (A) => B): Directive[B] = {
    fa match {
      case d: ComponentDirective[c, A] =>
        implicit val evc = d.evc
        d match {
          case cd: ComponentDirective.Create[c, A] =>
            cd.copy(result = cd.result andThen f)
          case cd: ComponentDirective.Destroy[c, A] =>
            cd.copy(result = f(cd.result))
          case cd: ComponentDirective.Tags[c, t, A] =>
            implicit val evt = cd.evt
            cd.copy(result = cd.result andThen f)
          case cd: ComponentDirective.Tags.Add[c, t, A] =>
            implicit val evt = cd.evt
            cd.copy(result = cd.result andThen f)
          case cd: ComponentDirective.Tags.Remove[c, t, A] =>
            implicit val evt = cd.evt
            cd.copy(result = cd.result andThen f)
        }
      case d: EngineDirective[e, A] =>
        implicit val eve = d.eve
        d match {
          case ed: EngineDirective.Components[e, c, A] =>
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Add[e, c, A] =>
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Remove[e, c, A] =>
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Indexed[e, k, c, A] =>
            implicit val evk = ed.evk
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Indexed.Index[e, k, c, A] =>
            implicit val evk = ed.evk
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Indexed.Unindex[e, k, c, A] =>
            implicit val evk = ed.evk
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Tagged[e, t, c, A] =>
            implicit val evt = ed.evt
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Tagged.Tag[e, t, c, A] =>
            implicit val evt = ed.evt
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Components.Tagged.Untag[e, t, c, A] =>
            implicit val evt = ed.evt
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities[e, en, A] =>
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Add[e, en, A] =>
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Remove[e, en, A] =>
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Indexed[e, k, en, A] =>
            implicit val evk = ed.evk
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Indexed.Index[e, k, en, A] =>
            implicit val evk = ed.evk
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Indexed.Unindex[e, k, en, A] =>
            implicit val evk = ed.evk
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Tagged[e, t, en, A] =>
            implicit val evt = ed.evt
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Tagged.Tag[e, t, en, A] =>
            implicit val evt = ed.evt
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Entities.Tagged.Untag[e, t, en, A] =>
            implicit val evt = ed.evt
            implicit val even = ed.even
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Keys[e, k, A] =>
            implicit val evk = ed.evk
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Keys.Add[e, k, A] =>
            implicit val evk = ed.evk
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Keys.Remove[e, k, A] =>
            implicit val evk = ed.evk
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Keys.Tagged[e, t, k, A] =>
            implicit val evt = ed.evt
            implicit val evk = ed.evk
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Keys.Tagged.Tag[e, t, k, A] =>
            implicit val evt = ed.evt
            implicit val evk = ed.evk
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Keys.Tagged.Untag[e, t, k, A] =>
            implicit val evt = ed.evt
            implicit val evk = ed.evk
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems[e, s, A] =>
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Add[e, s, A] =>
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Remove[e, s, A] =>
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Indexed[e, k, s, A] =>
            implicit val evk = ed.evk
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Indexed.Index[e, k, s, A] =>
            implicit val evk = ed.evk
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Indexed.Unindex[e, k, s, A] =>
            implicit val evk = ed.evk
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Tagged[e, t, s, A] =>
            implicit val evt = ed.evt
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Tagged.Tag[e, t, s, A] =>
            implicit val evt = ed.evt
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Systems.Tagged.Untag[e, t, s, A] =>
            implicit val evt = ed.evt
            implicit val evs = ed.evs
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Tags[e, t, A] =>
            implicit val evt = ed.evt
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Tags.Add[e, t, A] =>
            implicit val evt = ed.evt
            ed.copy(result = ed.result andThen f)
          case ed: EngineDirective.Tags.Remove[e, t, A] =>
            implicit val evt = ed.evt
            ed.copy(result = ed.result andThen f)

        }
      case d: EntityDirective[e, A] =>
        implicit val eve = d.eve
        d match {
          case ed: EntityDirective.Create[e, A] =>
            ed.copy(result = ed.result andThen f)
          case ed: EntityDirective.Destroy[e, A] =>
            ed.copy(result = f(ed.result))
          case ed: EntityDirective.Components[e, k, c, A] =>
            implicit val evk = ed.evk
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EntityDirective.Components.Put[e, k, c, A] =>
            implicit val evk = ed.evk
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EntityDirective.Components.Remove[e, k, c, A] =>
            implicit val evk = ed.evk
            implicit val evc = ed.evc
            ed.copy(result = ed.result andThen f)
          case ed: EntityDirective.Tags[e, t, A] =>
            implicit val evt = ed.evt
            ed.copy(result = ed.result andThen f)
          case ed: EntityDirective.Tags.Add[e, t, A] =>
            implicit val evt = ed.evt
            ed.copy(result = ed.result andThen f)
          case ed: EntityDirective.Tags.Remove[e, t, A] =>
            implicit val evt = ed.evt
            ed.copy(result = ed.result andThen f)
        }
      case d: KeyDirective[k, A] =>
        implicit val evk = d.evk
        d match {
          case kd: KeyDirective.Create[k, A] =>
            kd.copy(result = kd.result andThen f)
          case kd: KeyDirective.Destroy[k, A] =>
            kd.copy(result = f(kd.result))
          case kd: KeyDirective.Tags[k, t, A] =>
            implicit val evt = kd.evt
            kd.copy(result = kd.result andThen f)
          case kd: KeyDirective.Tags.Add[k, t, A] =>
            implicit val evt = kd.evt
            kd.copy(result = kd.result andThen f)
          case kd: KeyDirective.Tags.Remove[k, t, A] =>
            implicit val evt = kd.evt
            kd.copy(result = kd.result andThen f)
        }
      case d: SystemDirective[s, A] =>
        implicit val evs = d.evs
        d match {
          case sd: SystemDirective.Create[s, A] =>
            sd.copy(result = sd.result andThen f)
          case sd: SystemDirective.Destroy[s, A] =>
            sd.copy(result = f(sd.result))
          case sd: SystemDirective.Tags[s, t, A] =>
            implicit val evt = sd.evt
            sd.copy(result = sd.result andThen f)
          case sd: SystemDirective.Tags.Add[s, t, A] =>
            implicit val evt = sd.evt
            sd.copy(result = sd.result andThen f)
          case sd: SystemDirective.Tags.Remove[s, t, A] =>
            implicit val evt = sd.evt
            sd.copy(result = sd.result andThen f)
        }
      case d: TagDirective[t, A] =>
        implicit val evt = d.evt
        d match {
          case td: TagDirective.Create[t, A] =>
            td.copy(result = td.result andThen f)
          case td: TagDirective.Destroy[t, A] =>
            td.copy(result = f(td.result))
        }
    }
  }
}
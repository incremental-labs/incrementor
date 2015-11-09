package ecs

import cats.Later

sealed trait Directive[+Next]

sealed abstract class ComponentDirective[C, +Next]
(implicit val evc: Component[C])
  extends Directive[Next]

object ComponentDirective {

  final case class Create[C, +Next]
  (component: Later[C], result: C => Next)
  (implicit evc: Component[C])
    extends ComponentDirective[C, Next]

  final case class Destroy[C, +Next]
  (component: C, result: Next)
  (implicit evc: Component[C])
    extends ComponentDirective[C, Next]

  final case class Tags[C, T, +Next]
  (component: C, result: Set[T] => Next)
  (implicit val evt: Tag[T], evc: Component[C])
    extends ComponentDirective[C, Next]

  object Tags {

    final case class Add[C, T, +Next]
    (component: C, tag: T, result: C => Next)
    (implicit val evt: Tag[T], evc: Component[C])
      extends ComponentDirective[C, Next]

    final case class Remove[C, T, +Next]
    (component: C, tag: T, result: C => Next)
    (implicit val evt: Tag[T], evc: Component[C])
      extends ComponentDirective[C, Next]

  }

}

sealed abstract class EngineDirective[E, +Next]
(implicit val eve: Engine[E])
  extends Directive[Next]

object EngineDirective {

  final case class Components[E, C, +Next]
  (engine: E, result: Set[C] => Next)
  (implicit val evc: Component[C], eve: Engine[E])
    extends EngineDirective[E, Next]

  object Components {

    final case class Add[E, C, +Next]
    (engine: E, component: C, result: E => Next)
    (implicit val evc: Component[C], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Remove[E, C, +Next]
    (engine: E, component: C, result: E => Next)
    (implicit val evc: Component[C], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Indexed[E, K, C, +Next]
    (engine: E, result: Map[K, C] => Next)
    (implicit val evk: Key[K], val evc: Component[C], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Indexed {

      final case class Index[E, K, C, +Next]
      (engine: E, entry: (K, C), result: E => Next)
      (implicit val evk: Key[K], val evc: Component[C], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Unindex[E, K, C, +Next]
      (engine: E, entry: (K, C), result: E => Next)
      (implicit val evk: Key[K], val evc: Component[C], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

    final case class Tagged[E, T, C, +Next]
    (engine: E, result: Map[T, Set[C]] => Next)
    (implicit val evt: ecs.Tag[T], val evc: Component[C], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Tagged {

      final case class Tag[E, T, C, +Next]
      (engine: E, entry: (T, C), result: E => Next)
      (implicit val evt: ecs.Tag[T], val evc: Component[C], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Untag[E, T, C, +Next]
      (engine: E, entry: (T, C), result: E => Next)
      (implicit val evt: ecs.Tag[T], val evc: Component[C], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

  }

  final case class Entities[E, En, +Next]
  (engine: E, result: Set[En] => Next)
  (implicit val even: Entity[En], eve: Engine[E])
    extends EngineDirective[E, Next]

  object Entities {

    final case class Add[E, En, +Next]
    (engine: E, entity: En, result: E => Next)
    (implicit val even: Entity[En], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Remove[E, En, +Next]
    (engine: E, entity: En, result: E => Next)
    (implicit val even: Entity[En], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Indexed[E, K, En, +Next]
    (engine: E, result: Map[K, En] => Next)
    (implicit val evk: Key[K], val even: Entity[En], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Indexed {

      final case class Index[E, K, En, +Next]
      (engine: E, entry: (K, En), result: E => Next)
      (implicit val evk: Key[K], val even: Entity[En], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Unindex[E, K, En, +Next]
      (engine: E, entry: (K, En), result: E => Next)
      (implicit val evk: Key[K], val even: Entity[En], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

    final case class Tagged[E, T, En, +Next]
    (engine: E, result: Map[T, Set[En]] => Next)
    (implicit val evt: ecs.Tag[T], val even: Entity[En], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Tagged {

      final case class Tag[E, T, En, +Next]
      (engine: E, entry: (T, En), result: E => Next)
      (implicit val evt: ecs.Tag[T], val even: Entity[En], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Untag[E, T, En, +Next]
      (engine: E, entry: (T, En), result: E => Next)
      (implicit val evt: ecs.Tag[T], val even: Entity[En], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

  }

  final case class Keys[E, K, +Next]
  (engine: E, result: Set[K] => Next)
  (implicit val evk: Key[K], eve: Engine[E])
    extends EngineDirective[E, Next]

  object Keys {

    final case class Add[E, K, +Next]
    (engine: E, key: K, result: E => Next)
    (implicit val evk: Key[K], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Remove[E, K, +Next]
    (engine: E, key: K, result: E => Next)
    (implicit val evk: Key[K], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Tagged[E, T, K, +Next]
    (engine: E, result: Map[T, Set[K]] => Next)
    (implicit val evt: ecs.Tag[T], val evk: Key[K], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Tagged {

      final case class Tag[E, T, K, +Next]
      (engine: E, entry: (T, K), result: E => Next)
      (implicit val evt: ecs.Tag[T], val evk: Key[K], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Untag[E, T, K, +Next]
      (engine: E, entry: (T, K), result: E => Next)
      (implicit val evt: ecs.Tag[T], val evk: Key[K], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

  }

  final case class Systems[E, S, +Next]
  (engine: E, result: Set[S] => Next)
  (implicit val evs: System[S], eve: Engine[E])
    extends EngineDirective[E, Next]

  object Systems {

    final case class Add[E, S, +Next]
    (engine: E, system: S, result: E => Next)
    (implicit val evs: System[S], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Remove[E, S, +Next]
    (engine: E, system: S, result: E => Next)
    (implicit val evs: System[S], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Indexed[E, K, S, +Next]
    (engine: E, result: Map[K, S] => Next)
    (implicit val evk: Key[K], val evs: System[S], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Indexed {

      final case class Index[E, K, S, +Next]
      (engine: E, entry: (K, S), result: E => Next)
      (implicit val evk: Key[K], val evs: System[S], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Unindex[E, K, S, +Next]
      (engine: E, entry: (K, S), result: E => Next)
      (implicit val evk: Key[K], val evs: System[S], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

    final case class Tagged[E, T, S, +Next]
    (engine: E, result: Map[T, Set[S]] => Next)
    (implicit val evt: ecs.Tag[T], val evs: System[S], eve: Engine[E])
      extends EngineDirective[E, Next]

    object Tagged {

      final case class Tag[E, T, S, +Next]
      (engine: E, entry: (T, S), result: E => Next)
      (implicit val evt: ecs.Tag[T], val evs: System[S], eve: Engine[E])
        extends EngineDirective[E, Next]

      final case class Untag[E, T, S, +Next]
      (engine: E, entry: (T, S), result: E => Next)
      (implicit val evt: ecs.Tag[T], val evs: System[S], eve: Engine[E])
        extends EngineDirective[E, Next]

    }

  }

  final case class Tags[E, T, +Next]
  (engine: E, result: Set[T] => Next)
  (implicit val evt: Tag[T], eve: Engine[E])
    extends EngineDirective[E, Next]

  object Tags {

    final case class Add[E, T, +Next]
    (engine: E, key: T, result: E => Next)
    (implicit val evt: Tag[T], eve: Engine[E])
      extends EngineDirective[E, Next]

    final case class Remove[E, T, +Next]
    (engine: E, key: T, result: E => Next)
    (implicit val evt: Tag[T], eve: Engine[E])
      extends EngineDirective[E, Next]

  }

}

sealed abstract class EntityDirective[E, +Next]
(implicit val eve: Entity[E])
  extends Directive[Next]

object EntityDirective {

  final case class Create[E, +Next]
  (entity: Later[E], result: E => Next)
  (implicit eve: Entity[E])
    extends EntityDirective[E, Next]

  final case class Destroy[E, +Next]
  (entity: E, result: Next)
  (implicit eve: Entity[E])
    extends EntityDirective[E, Next]

  final case class Components[E, K, C, +Next]
  (entity: E, result: Map[K, C] => Next)
  (implicit val evk: Key[K], val evc: Component[C], eve: Entity[E])
    extends EntityDirective[E, Next]

  object Components {

    final case class Put[E, K, C, +Next]
    (entity: E, entry: (K, C), result: E => Next)
    (implicit val evk: Key[K], val evc: Component[C], eve: Entity[E])
      extends EntityDirective[E, Next]

    final case class Remove[E, K, C, +Next]
    (entity: E, entry: (K, C), result: E => Next)
    (implicit val evk: Key[K], val evc: Component[C], eve: Entity[E])
      extends EntityDirective[E, Next]

  }

  final case class Tags[E, T, +Next]
  (entity: E, result: Set[T] => Next)
  (implicit val evt: Tag[T], eve: Entity[E])
    extends EntityDirective[E, Next]

  object Tags {

    final case class Add[E, T, +Next]
    (entity: E, tag: T, result: E => Next)
    (implicit val evt: Tag[T], eve: Entity[E])
      extends EntityDirective[E, Next]

    final case class Remove[E, T, +Next]
    (entity: E, tag: T, result: E => Next)
    (implicit val evt: Tag[T], eve: Entity[E])
      extends EntityDirective[E, Next]

  }

}

sealed abstract class KeyDirective[K, +Next]
(implicit val evk: Key[K])
  extends Directive[Next]

object KeyDirective {

  final case class Create[K, +Next]
  (key: Later[K], result: K => Next)
  (implicit evk: Key[K])
    extends KeyDirective[K, Next]

  final case class Destroy[K, +Next]
  (key: K, result: Next)
  (implicit evk: Key[K])
    extends KeyDirective[K, Next]

  final case class Tags[K, T, +Next]
  (key: K, result: Set[T] => Next)
  (implicit val evt: Tag[T], evk: Key[K])
    extends KeyDirective[K, Next]

  object Tags {

    final case class Add[K, T, +Next]
    (key: K, tag: T, result: K => Next)
    (implicit val evt: Tag[T], evk: Key[K])
      extends KeyDirective[K, Next]

    final case class Remove[K, T, +Next]
    (key: K, tag: T, result: K => Next)
    (implicit val evt: Tag[T], evk: Key[K])
      extends KeyDirective[K, Next]

  }

}

sealed abstract class SystemDirective[S, +Next]
(implicit val evs: System[S])
  extends Directive[Next]

object SystemDirective {

  final case class Create[S, +Next]
  (system: Later[S], result: S => Next)
  (implicit evs: System[S])
    extends SystemDirective[S, Next]

  final case class Destroy[S, +Next]
  (system: S, result: Next)
  (implicit evs: System[S])
    extends SystemDirective[S, Next]

  final case class Tags[S, T, +Next]
  (system: S, result: Set[T] => Next)
  (implicit val evt: Tag[T], evs: System[S])
    extends SystemDirective[S, Next]

  object Tags {

    final case class Add[S, T, +Next]
    (system: S, tag: T, result: S => Next)
    (implicit val evt: Tag[T], evs: System[S])
      extends SystemDirective[S, Next]

    final case class Remove[S, T, +Next]
    (system: S, tag: T, result: S => Next)
    (implicit val evt: Tag[T], evs: System[S])
      extends SystemDirective[S, Next]

  }

}

sealed abstract class TagDirective[T, +Next]
(implicit val evt: Tag[T])
  extends Directive[Next]

object TagDirective {

  final case class Create[T, +Next]
  (tag: Later[T], result: T => Next)
  (implicit evt: Tag[T])
    extends TagDirective[T, Next]

  final case class Destroy[T, +Next]
  (tag: T, result: Next)
  (implicit evt: Tag[T])
    extends TagDirective[T, Next]

}
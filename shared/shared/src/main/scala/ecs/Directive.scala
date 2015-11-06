package ecs

sealed trait Directive[+Next]
object Directive

sealed trait EntityDirective[+Next] extends Directive[Next]
object EntityDirective {


  final case class AddTag[E: Entity, T: Tag, +Next]
  (entity: E, tag: T, result: Next)
    extends EntityDirective[Next]

  final case class HasTag[E: Entity, T: Tag, +Next]
  (entity: E, tag: T, result: Boolean => Next)
    extends EntityDirective[Next]

  final case class RemoveTag[E: Entity, T: Tag, +Next]
  (entity: E, tag: T, result: Option[T] => Next)
    extends EntityDirective[Next]


  final case class AddComponent[E: Entity, K: Key, C: Component, +Next]
  (entity: E, key: K, component: C, result: Next)
    extends EntityDirective[Next]

  final case class GetComponent[E: Entity, K: Key, C: Component, +Next]
  (entity: E, key: K, result: Option[C] => Next)
    extends EntityDirective[Next]

  final case class HasComponent[E: Entity, K: Key, +Next]
  (entity: E, key: K, result: Boolean => Next)
    extends EntityDirective[Next]

  final case class RemoveComponent[E: Entity, K: Key, C: Component, +Next]
  (entity: E, key: K, result: Option[C] => Next)
    extends EntityDirective[Next]

  final case class SetComponent[E: Entity, K: Key, C1: Component, C2: Component, +Next]
  (entity: E, key: String, component: C2, result: Option[C1] => Next)
    extends EntityDirective[Next]

}


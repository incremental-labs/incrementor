package model


package object mutable {
  type Position = model.Position
  implicit def Position = model.Position _
}

package model

package object immutable {
  type Position = model.Position
  implicit def Position = model.Position _
}

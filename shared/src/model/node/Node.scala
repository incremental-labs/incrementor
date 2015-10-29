package model.node

import model.Displayed

sealed trait Node[+T <: NodeType, +I <: NodeData[T]] extends Displayed {

  def nodeType: T
  def nodeInfo: I

}


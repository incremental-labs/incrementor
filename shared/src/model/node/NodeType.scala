package model.node

import model.Displayed

sealed abstract class NodeType(val abbreviated: String) extends Displayed {
  override val display: String = s"$abbreviated node"
  override val plural: String = display + "s"
}

case object DataNode extends NodeType("data")
case object EmptyNode extends NodeType("empty")
case object MemoryNode extends NodeType("memory")
case object PowerNode extends NodeType("power")
case object ProcessingNode extends NodeType("processing")


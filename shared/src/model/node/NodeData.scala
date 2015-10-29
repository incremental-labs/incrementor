package model.node

import model.Displayed

sealed abstract class NodeData[+T <: NodeType](val nodeType: T, val abbreviated: String) extends Displayed {
  override val display: String = s"$abbreviated node data"
  override val plural: String = display
}

case object DataNodeData extends NodeData(DataNode, "data")
case object EmptyNodeData extends NodeData(EmptyNode, "empty")
case object MemoryNodeData extends NodeData(MemoryNode, "memory")
case object PowerNodeData extends NodeData(PowerNode, "power")
case object ProcessingNodeData extends NodeData(ProcessingNode, "processing")
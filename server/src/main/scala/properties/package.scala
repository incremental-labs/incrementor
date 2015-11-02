import com.typesafe.config.Config

package object properties {

  case object server extends Property[AnyRef] {

    case object timeout extends Property[Int]
    case object index extends Property[String]

    case object resource extends Property[AnyRef] {

      case object dir extends Property[String]
      case object route extends Property[String]

    }
  }

}

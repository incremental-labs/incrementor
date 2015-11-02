lazy val incrementor =
  (project in file("."))
  .settings(common : _ *)
  .settings(
    name := "incrementor"
  )
  .aggregate(`server`, `shared-jvm`, `shared-js`, `web`)

lazy val `server` =
(project in file("server"))
  .settings(common : _ *)
  .settings(
    name := "server",
    libraryDependencies ++= Seq(
      "io.spray" %% "spray-can" % "1.3.1",
      "io.spray" %% "spray-routing" % "1.3.3",
      "com.wandoulabs.akka" %% "spray-websocket" % "0.1.4"
    )
  )
  .dependsOn(`shared-jvm`)

lazy val `shared` =
  crossProject.crossType(CrossType.Pure) in file("shared")

lazy val `shared-jvm` =
  `shared`.jvm
    .settings(common : _ *)
    .settings(name := "shared-jvm")

lazy val `shared-js` =
  `shared`.js
    .settings(common : _ *)
    .settings(name := "shared-js")

lazy val `web` =
(project in file("web"))
  .enablePlugins(ScalaJSPlugin)
  .settings(common : _ *)
  .settings(
    name := "web",
    (scalaJSStage in Global) := FastOptStage,
    libraryDependencies ++= Seq(
      "be.doeraene" %%% "scalajs-jquery" % "0.8.1",
      "org.scala-js" %%% "scalajs-dom" % "0.8.0"
    )
  )
  .dependsOn(`shared-js`)

lazy val common = Seq(
  organization := "Incremental Labs",
  version :="0.1.0",
  scalaVersion := "2.11.7"
)

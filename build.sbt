lazy val incrementor =
  (project in file("."))
  .settings(common : _ *)
  .settings(
    name := "incrementor"
  )
  .aggregate(`server`, `shared`, `web`)

lazy val `server` =
(project in file("server"))
  .settings(common : _ *)
  .settings(
    name := "server",
    libraryDependencies ++= Seq(
      "io.spray" %% "spray-can" % "1.3.1",
      "io.spray" %% "spray-routing" % "1.3.3"
    )
  )
  .dependsOn(`shared`)

lazy val `shared` =
(project in file("shared"))
  .settings(common : _ *)
  .settings(
    name := "shared"
  )

lazy val `web` =
(project in file("web"))
  .settings(common : _ *)
  .settings(
    name := "web"
  )
  .dependsOn(`shared`)

lazy val common = Seq(
organization := "Incremental Labs",
version :="0.1.0"
)

lazy val incrementor =
  (project in file("."))
    .settings(common: _ *)
    .settings(
      name := "incrementor"
    )
    .aggregate(`server`, `shared-js`, `shared-jvm`, `web`)

lazy val `server` =
  (project in file("server"))
    .settings(common: _ *)
    .settings(
      name := "server",
      libraryDependencies ++= Seq(
        "io.spray" %% "spray-can" % "1.3.1",
        "io.spray" %% "spray-routing" % "1.3.3",
        "org.reactivemongo" %% "reactivemongo" % "0.11.7",
        "com.typesafe.play" %% "play-iteratees" % "2.3.10"
      )
    )
    .dependsOn(`shared-jvm`)

lazy val `shared` =
  (crossProject.crossType(CrossType.Pure) in file("shared"))
    .settings(common: _ *)
    .settings(
      name := "shared"
    )

lazy val `shared-js` = `shared`.js
lazy val `shared-jvm` = `shared`.jvm

lazy val `web` =
  (project in file("web"))
    .enablePlugins(ScalaJSPlugin)
    .settings(common: _ *)
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
  version := "0.1.0",
  scalaVersion := "2.11.7",

  resolvers += Resolver.sonatypeRepo("snapshots"),

  (scalaSource in Compile) := baseDirectory.value / "src",
  (javaSource in Compile) := baseDirectory.value / "src",
  (resourceDirectory in Compile) := baseDirectory.value / "resources",

  (scalaSource in Test) := baseDirectory.value / "test",
  (javaSource in Test) := baseDirectory.value / "test",
  (resourceDirectory in Test) := baseDirectory.value / "test-resources"
)

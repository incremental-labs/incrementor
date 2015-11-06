import sbt.Keys._
import sbt.inc.Analysis

lazy val webAppDir = settingKey[File]("root directory for the static web app")

lazy val common = Seq(
  organization := "Incremental Labs",
  version := "0.1.0",
  scalaVersion := "2.11.7"
)

lazy val incrementor =
  (project in file("."))
    .settings(common: _ *)
    .settings(
      name := "incrementor"
    ).aggregate(server, sharedJVM, sharedJS, web)

lazy val server =
  (project in file("server"))
    .settings(common: _ *)
    .settings(Revolver.settings: _ *)
    .settings(
      name := "server",

      Revolver.reStart <<= Revolver.reStart.dependsOn(fastOptJS in Compile in web, packageJSDependencies in Compile in web),
      Revolver.reForkOptions <<=
        (Revolver.reForkOptions, webAppDir in web)
          .map((options: ForkOptions, appDir: File) =>
            options.copy(workingDirectory = Some(appDir))),

      libraryDependencies ++= Seq(
        "io.spray" %% "spray-can" % "1.3.2",
        "com.wandoulabs.akka" %% "spray-websocket" % "0.1.4"
      )
    )
    .dependsOn(sharedJVM)

lazy val shared =
  (crossProject in file("shared"))
    .settings(common: _ *)
    .settings(
      libraryDependencies ++= Seq(
        "org.spire-math" %%% "cats" % "0.2.0",

        // Intellij shows an error on this dep, but it's wrong
        compilerPlugin("org.scalamacros" %% "paradise" % "2.1.0-M5" cross CrossVersion.full)
      )
    )

lazy val sharedJS = shared.js
lazy val sharedJVM = shared.jvm

lazy val web =
  (project in file("web"))
    .enablePlugins(ScalaJSPlugin)
    .settings(common: _ *)
    .settings(

      webAppDir := baseDirectory.value / "app",

      crossTarget in fastOptJS in Compile := webAppDir.value / "assets" / "js",
      crossTarget in packageJSDependencies in Compile := webAppDir.value / "assets" / "js",

      skip in fastOptJS in Compile := false,
      skip in packageJSDependencies in Compile := false,

      (scalaJSStage in Global) := FastOptStage,

      libraryDependencies ++= Seq(
        "be.doeraene" %%% "scalajs-jquery" % "0.8.1",
        "org.scala-js" %%% "scalajs-dom" % "0.8.0"
      )

    )
    .dependsOn(sharedJS)

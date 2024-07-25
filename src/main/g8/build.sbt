import Dependencies._

val commonSettings = Seq(
  scalacOptions ++= Seq(
    "-Wunused:all",
    "-rewrite",
    "-no-indent"
  )
)

lazy val example = project
    .in(file("example"))
    .dependsOn(root)
    .settings(
        name := "example",
        publish / skip := true,
        commonSettings,
    )
    .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

lazy val tests = project
    .in(file("tests"))
    .dependsOn(root)
    .settings(
        name := "tests",
        publish / skip := true,
        libraryDependencies ++= {
            Seq(
                munit.value % Test,
            )
        },
        commonSettings,
    )
    .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

lazy val root = project
    .in(file("."))
    .settings(
        name := (ThisBuild / name).value,
        commonSettings,
    )
    .enablePlugins(ScalafixPlugin, AutomateHeaderPlugin)

/* project settings */
ThisBuild / scalaVersion := "$scalaVersion$"
ThisBuild / name := "$name$"
ThisBuild / version := "0.1.0-SNAPSHOT"
ThisBuild / organization := "$organization$"
ThisBuild / organizationName := "$organizationName$"
ThisBuild / description := "$description$"
ThisBuild / licenses := List(("MIT", url("https://opensource.org/license/mit")))
ThisBuild / startYear := Some($startYear$)
ThisBuild / homepage := Some(url("https://github.com/$organizationName$/$name$"))
ThisBuild / scmInfo := Some(
    ScmInfo(
        url("https://github.com/$organizationName$/$name$"),
        "git@github.com:$organizationName$/$name$.git"
    )
)

ThisBuild / developers ++= List(
    Developer(
        id      = "$githubName$", 
        name    = "$fullName$", 
        email   = "$email$", 
        url     = url("https://github.com/$githubName$")
    ),
)

/* test settings */
ThisBuild / testFrameworks += new TestFramework("munit.Framework")

/* scalafix settings */
ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

/* scaladoc settings */
ThisBuild / autoAPIMappings := true
Compile / doc / scalacOptions ++= Seq(
    "-doc-title", (ThisBuild / name).value,
    "-project-version", (ThisBuild / version).value,
    // "-project-logo", "docs/icon.jpeg",
)

/* publish settings */
ThisBuild / publishMavenStyle := true


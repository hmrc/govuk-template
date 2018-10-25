/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import play.sbt.PlayLayoutPlugin
import play.sbt.routes.RoutesKeys.{InjectedRoutesGenerator, StaticRoutesGenerator, routesGenerator}
import sbt.Keys._
import sbt.{Build, _}
import uk.gov.hmrc.SbtArtifactory.autoImport.makePublicallyAvailableOnBintray
import uk.gov.hmrc.{SbtArtifactory, SbtAutoBuildPlugin}
import uk.gov.hmrc.playcrosscompilation.PlayVersion.Play25
import uk.gov.hmrc.versioning.SbtGitVersioning
import uk.gov.hmrc.versioning.SbtGitVersioning.autoImport.majorVersion

object HmrcBuild extends Build {

  val appName = "govuk-template"

  import play.twirl.sbt.Import.TwirlKeys._

  lazy val library = Project(appName, file("."))
    .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
    .disablePlugins(PlayLayoutPlugin)
    .settings(
      majorVersion := 5,
      makePublicallyAvailableOnBintray := true,
      name := appName,
      scalaVersion := "2.11.7",
      libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
      dependencyOverrides ++= LibDependencies.overrides,
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        Resolver.typesafeRepo("releases")
      ),
      crossScalaVersions := Seq("2.11.7"),
      routesGenerator    := {
        if (PlayCrossCompilation.playVersion == Play25) {
          StaticRoutesGenerator
        } else {
          InjectedRoutesGenerator
        }
      },
      (sourceDirectories in (Compile, compileTemplates)) += {
        val twirlDir =
          if (PlayCrossCompilation.playVersion == Play25) {
            "src/main/play-25/twirl"
          } else {
            "src/main/play-26/twirl"
          }
        baseDirectory.value / twirlDir
      },
      de.heikoseeberger.sbtheader.HeaderKey.excludes += "**.mustache.html", // don't add licence headers to mustache templates
      PlayCrossCompilation.playCrossCompilationSettings
    )
    .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
    .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)

}

object LibDependencies {

  val compile: Seq[ModuleID] =
    PlayCrossCompilation.dependencies(
      play25 = Seq(
        "com.typesafe.play" %% "play" % "2.5.12" % "provided"
      ),
      play26 = Seq(
        "com.typesafe.play" %% "play" % "2.6.20" % "provided"
      )
    )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    "org.pegdown"   %  "pegdown"   % "1.6.0" % Test
  )

  // downgrade twirl for play 2.5 compatibility
  val overrides: Set[ModuleID] =
    PlayCrossCompilation.dependencies(
      play25 = Seq(
        "com.typesafe.play" %% "twirl-api" % "1.1.1"
      )
    ).toSet

}


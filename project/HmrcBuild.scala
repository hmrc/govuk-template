/*
 * Copyright 2015 HM Revenue & Customs
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

import sbt.Keys._
import sbt.{Build, _}
import uk.gov.hmrc.SbtAutoBuildPlugin
import uk.gov.hmrc.versioning.SbtGitVersioning

object HmrcBuild extends Build {

  import Dependencies._
  import uk.gov.hmrc.DefaultBuildSettings._

  val appName = "govuk-template"

  val appDependencies = Seq(
    Compile.play
  )

  lazy val library = Project(appName, file("."))
    .enablePlugins(play.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
    .settings(
      name := appName,
      targetJvm := "jvm-1.7",
      libraryDependencies ++= appDependencies,
      resolvers := Seq(
        Resolver.bintrayRepo("hmrc", "releases"),
        Resolver.typesafeRepo("releases")
      ),
      crossScalaVersions := Seq("2.11.7")
    )
    .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
    .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
}

object Dependencies {
  import _root_.play.core.PlayVersion
  object Compile {
    val play = "com.typesafe.play" %% "play" % PlayVersion.current % "provided"
  }
}

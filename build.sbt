import uk.gov.hmrc.playcrosscompilation.PlayVersion.Play25

val appName = "govuk-template"

lazy val library = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 5,
    makePublicallyAvailableOnBintray := true,
    name := appName,
    scalaVersion := {
      if (PlayCrossCompilation.playVersion == Play25) "2.11.12"
      else "2.12.8"
    },
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    dependencyOverrides ++= LibDependencies.overrides,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    crossScalaVersions := Seq("2.11.12", "2.12.8"),
    routesGenerator    := {
      if (PlayCrossCompilation.playVersion == Play25) {
        StaticRoutesGenerator
      } else {
        InjectedRoutesGenerator
      }
    },
    (sourceDirectories in (Compile, TwirlKeys.compileTemplates)) += {
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

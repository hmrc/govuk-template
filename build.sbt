val scala2_13 = "2.13.7"

lazy val root = Project("govuk-template-play-29", file("."))
  .enablePlugins(play.sbt.PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 6,
    isPublicArtefact := true,
    scalaVersion := scala2_13,
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    routesGenerator    := InjectedRoutesGenerator,
    (sourceDirectories in (Compile, TwirlKeys.compileTemplates)) += baseDirectory.value / "src/main/twirl"
    ,
    excludeFilter.in(unmanagedResources.in(headerCreate)) := "*.mustache.html" // don't add licence headers to mustache templates
  )
  .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)

val appName = "govuk-template"

val scala2_12 = "2.12.12"
val scala2_13 = "2.13.7"

lazy val library = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 5,
    makePublicallyAvailableOnBintray := true,
    name := appName,
    scalaVersion := scala2_12,
    crossScalaVersions := Seq(scala2_12, scala2_13),
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    routesGenerator    := InjectedRoutesGenerator,
    (sourceDirectories in (Compile, TwirlKeys.compileTemplates)) += baseDirectory.value / "src/main/twirl"
    ,
    excludeFilter.in(unmanagedResources.in(headerCreate)) := "*.mustache.html" // don't add licence headers to mustache templates
  )
  .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)

val appName = "govuk-template"

lazy val library = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 5,
    makePublicallyAvailableOnBintray := true,
    name := appName,
    scalaVersion := "2.12.12",
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    routesGenerator    := InjectedRoutesGenerator,
    (sourceDirectories in (Compile, TwirlKeys.compileTemplates)) += baseDirectory.value / "src/main/twirl"
    ,
    excludeFilter.in(unmanagedResources.in(headerCreate)) := "*.mustache.html", // don't add licence headers to mustache templates
    PlayCrossCompilation.playCrossCompilationSettings
  )
  .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)

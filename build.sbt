val scala2_13 = "2.13.7"

lazy val root = Project("govuk-template-play-29", file("."))
  .enablePlugins(play.sbt.PlayScala)
  .disablePlugins(PlayLayoutPlugin)
  .disablePlugins(JUnitXmlReportPlugin) // Required to prevent https://github.com/scalatest/scalatest/issues/1427
  .settings(
      majorVersion := 6,
      scalaVersion := scala2_13,
      crossScalaVersions := Seq(scala2_13),
      isPublicArtefact := true,
      libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
      routesGenerator    := InjectedRoutesGenerator,
      (sourceDirectories in (Compile, TwirlKeys.compileTemplates)) += baseDirectory.value / "src/main/twirl"
      ,
      excludeFilter.in(unmanagedResources.in(headerCreate)) := "*.mustache.html", // don't add licence headers to mustache templates
      PlayCrossCompilation.playCrossCompilationSettings
  )
  .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)
  .settings(TwirlKeys.constructorAnnotations += "@javax.inject.Inject()")
  .settings(unmanagedSourceDirectories in sbt.Compile += baseDirectory.value / "src/main/twirl")

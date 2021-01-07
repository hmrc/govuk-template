val appName = "govuk-template"

lazy val library = Project(appName, file("."))
  .enablePlugins(play.sbt.PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .disablePlugins(PlayLayoutPlugin)
  .settings(
    majorVersion := 5,
    makePublicallyAvailableOnBintray := true,
    name := appName,
    scalaVersion := "2.12.10",
    libraryDependencies ++= LibDependencies.compile ++ LibDependencies.test,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    crossScalaVersions := Seq("2.11.12", "2.12.10"),
    routesGenerator    := InjectedRoutesGenerator,
    (sourceDirectories in (Compile, TwirlKeys.compileTemplates)) += {
      val twirlDir = "src/main/play-26/twirl"
      baseDirectory.value / twirlDir
    },
    excludeFilter.in(unmanagedResources.in(headerCreate)) := "*.mustache.html", // don't add licence headers to mustache templates
    PlayCrossCompilation.playCrossCompilationSettings
  )
  .settings(unmanagedResourceDirectories in sbt.Compile += baseDirectory.value / "resources")
  .disablePlugins(sbt.plugins.JUnitXmlReportPlugin)

def loadObject[T](objectName: String): T = {
  import scala.reflect.runtime.universe
  val runtimeMirror = universe.runtimeMirror(getClass.getClassLoader)
  val module = runtimeMirror.staticModule(objectName)
  val obj = runtimeMirror.reflectModule(module)
  obj.instance.asInstanceOf[T]
}

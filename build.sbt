import play.core.PlayVersion

val libName = "govuk-template"

lazy val root = Project(libName, file("."))
  .enablePlugins(PlayScala, SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory)
  .disablePlugins(JUnitXmlReportPlugin)
  .settings(
    name := libName,
    majorVersion := 5,
    scalaVersion := "2.11.7",
    libraryDependencies ++= libDependencies,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    routesGenerator := StaticRoutesGenerator,
    unmanagedResourceDirectories in Compile += baseDirectory.value / "resources",
    excludes += "**.mustache.html"
  )

lazy val libDependencies = Seq(
  "com.typesafe.play" %% "play" % PlayVersion.current % "provided"
)
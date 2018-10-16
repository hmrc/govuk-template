import play.core.PlayVersion

val libName = "govuk-template"

lazy val root = Project(libName, file("."))
  .enablePlugins(PlayScala, SbtAutoBuildPlugin, SbtGitVersioning)
  .disablePlugins(JUnitXmlReportPlugin)
  .settings(
    name := libName,
    scalaVersion := "2.11.7",
    libraryDependencies ++= libDependencies,
    resolvers := Seq(
      Resolver.bintrayRepo("hmrc", "releases"),
      Resolver.typesafeRepo("releases")
    ),
    routesGenerator := StaticRoutesGenerator,
    unmanagedResourceDirectories in Compile += baseDirectory.value / "resources"
  )

lazy val libDependencies = Seq(
  "com.typesafe.play" %% "play" % PlayVersion.current % "provided"
)
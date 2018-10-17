import PlayCrossCompilation.dependencies
import uk.gov.hmrc.playcrosscompilation.PlayVersion.{Play25, Play26}

val libName = "govuk-template"

lazy val root = Project(libName, file("."))
  .enablePlugins(SbtAutoBuildPlugin, SbtGitVersioning, SbtArtifactory, SbtTwirl)
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
    unmanagedSourceDirectories in Compile += baseDirectory.value / "src/main/twirl",
    unmanagedResourceDirectories in Compile += baseDirectory.value / "resources",
    excludes += "**.mustache.html",
    makePublicallyAvailableOnBintray := true,
    TwirlKeys.constructorAnnotations += "@javax.inject.Inject()",
    TwirlKeys.templateImports := templateImports,
    PlayCrossCompilation.playCrossCompilationSettings
  )

lazy val libDependencies: Seq[ModuleID] = dependencies(
  shared = Seq(
    "com.typesafe.play" %% "play" % PlayCrossCompilation.playLibVersion
  ),
  play25 = Seq(
    "com.typesafe.play" %% "twirl-api" % "1.1.1" force()
  )
)

lazy val templateImports: Seq[String] = {

  val allImports = Seq(
    "_root_.play.twirl.api.Html",
    "_root_.play.twirl.api.JavaScript",
    "_root_.play.twirl.api.Txt",
    "_root_.play.twirl.api.Xml",
    "play.api.mvc._",
    "play.api.data._",
    "play.api.i18n._",
    "play.api.templates.PlayMagic._",
    "uk.gov.hmrc.template.TemplateAssetsFinder"
  )

  val specificImports = PlayCrossCompilation.playVersion match {
    case Play25 => Seq(
      "_root_.play.twirl.api.TemplateMagic._"
    )
    case Play26 => Seq(
      "_root_.play.twirl.api.TwirlFeatureImports._",
      "_root_.play.twirl.api.TwirlHelperImports._"
    )
  }

  allImports ++ specificImports
}

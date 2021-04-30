resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"
resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "2.15.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "2.1.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "1.0.0")

addSbtPlugin(
  sys.env.get("PLAY_VERSION") match {
    case Some("2.8") => "com.typesafe.play" % "sbt-plugin" % "2.8.7"
    case Some("2.7") => "com.typesafe.play" % "sbt-plugin" % "2.7.9"
    case _           => "com.typesafe.play" % "sbt-plugin" % "2.6.25"
  }
)

addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "2.0.0")


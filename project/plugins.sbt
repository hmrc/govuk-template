resolvers += "HMRC-open-artefacts-maven" at "https://open.artefacts.tax.service.gov.uk/maven2"
resolvers += Resolver.url("HMRC-open-artefacts-ivy", url("https://open.artefacts.tax.service.gov.uk/ivy2"))(Resolver.ivyStylePatterns)

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "3.0.0")

val playPlugin =
   sys.env.getOrElse("PLAY_VERSION", "2.6") match {
    case "2.6" => "com.typesafe.play" % "sbt-plugin" % "2.6.20"
    case "2.7" => "com.typesafe.play" % "sbt-plugin" % "2.7.4"
    case "2.8" => "com.typesafe.play" % "sbt-plugin" % "2.8.7"
  }

addSbtPlugin(playPlugin)

addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "2.0.0")


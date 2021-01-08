resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "HMRC Releases" at "https://dl.bintray.com/hmrc/releases"

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "2.10.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "2.1.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "1.0.0")

val playPlugin =
   sys.env.getOrElse("PLAY_VERSION", "2.6") match {
    case "2.6" => "com.typesafe.play" % "sbt-plugin" % "2.6.20"
    case "2.7" => "com.typesafe.play" % "sbt-plugin" % "2.7.4"
  }

addSbtPlugin(playPlugin)

addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "0.20.0")


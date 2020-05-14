resolvers += Resolver.url("hmrc-sbt-plugin-releases",
  url("https://dl.bintray.com/hmrc/sbt-plugin-releases"))(Resolver.ivyStylePatterns)

resolvers += "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/"

resolvers += "HMRC Releases" at "https://dl.bintray.com/hmrc/releases"

addSbtPlugin("uk.gov.hmrc" % "sbt-auto-build" % "2.6.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-git-versioning" % "2.1.0")

addSbtPlugin("uk.gov.hmrc" % "sbt-artifactory" % "1.0.0")

val playPlugin =
  if (sys.env.get("PLAY_VERSION").exists(_ == "2.6"))
    "com.typesafe.play" % "sbt-plugin" % "2.6.20"
  else
    "com.typesafe.play" % "sbt-plugin" % "2.5.19"

addSbtPlugin(playPlugin)

addSbtPlugin("uk.gov.hmrc" % "sbt-play-cross-compilation" % "0.20.0")


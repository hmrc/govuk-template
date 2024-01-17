import sbt.{ModuleID, _}

object LibDependencies {

  val compile: Seq[ModuleID] = Seq(
    "com.typesafe.play" %% "play" % "2.8.7" % "provided"
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.0.8" % Test,
    "org.jsoup" % "jsoup" % "1.12.1" % Test,
    "com.vladsch.flexmark" % "flexmark-all" % "0.35.10" % Test,
    "org.pegdown" % "pegdown" % "1.6.0" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
  )
}

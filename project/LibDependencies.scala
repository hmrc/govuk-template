import sbt.{ModuleID, _}

object LibDependencies {

  val compile: Seq[ModuleID] =
    PlayCrossCompilation.dependencies(
      play25 = Seq(
        "com.typesafe.play" %% "play" % "2.5.19" % "provided"
      ),
      play26 = Seq(
        "com.typesafe.play" %% "play" % "2.6.20" % "provided"
      )
    )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest" %% "scalatest" % "3.0.5" % Test,
    "org.pegdown"   %  "pegdown"   % "1.6.0" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "2.0.1" % Test,
    "org.jsoup" % "jsoup" % "1.12.1" % Test
  )

  // downgrade twirl for play 2.5 compatibility
  val overrides: Set[ModuleID] =
    PlayCrossCompilation.dependencies(
      play25 = Seq(
        "com.typesafe.play" %% "twirl-api" % "1.1.1"
      )
    ).toSet

}

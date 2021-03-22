import sbt.{ModuleID, _}

object LibDependencies {

  val compile: Seq[ModuleID] =
    PlayCrossCompilation.dependencies(
      play26 = Seq(
        "com.typesafe.play" %% "play" % "2.6.20" % "provided"
      ),
      play27 = Seq(
        "com.typesafe.play" %% "play" % "2.7.4" % "provided"
      )
    )

  val test: Seq[ModuleID] =
    PlayCrossCompilation.dependencies(
      shared = Seq(
        "org.scalatest" %% "scalatest" % "3.0.8"  % Test,
        "org.pegdown"    % "pegdown"   % "1.6.0"  % Test,
        "org.jsoup"      % "jsoup"     % "1.12.1" % Test
      ),
      play26 = Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.2" % Test
      ),
      play27 = Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
      )
    )
}

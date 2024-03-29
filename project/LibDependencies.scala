import sbt.{ModuleID, _}

object LibDependencies {

  val compile: Seq[ModuleID] = Seq(
    "org.playframework" %% "play" % "3.0.0"
  )

  val test: Seq[ModuleID] = Seq(
    "org.scalatest"          %% "scalatest"          % "3.2.17" % Test,
    "org.jsoup"               % "jsoup"              % "1.13.1" % Test,
    "com.vladsch.flexmark"    % "flexmark-all"       % "0.64.8" % Test,
    "org.scalatestplus.play" %% "scalatestplus-play" % "7.0.0"  % Test
  )
}

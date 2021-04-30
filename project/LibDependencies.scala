import play.core.PlayVersion
import sbt.{ModuleID, _}

object LibDependencies {

  val compile: Seq[ModuleID] =
    PlayCrossCompilation.dependencies(
      shared = Seq(
        "com.typesafe.play" %% "play"            % PlayVersion.current
      )
    )

  val test: Seq[ModuleID] =
    PlayCrossCompilation.dependencies(
      shared = Seq(
        "org.scalatest"          %% "scalatest"          % "3.0.8"    % Test,
        "org.pegdown"            %  "pegdown"            % "1.6.0"    % Test,
        "org.jsoup"              % "jsoup"               % "1.12.1"   % Test
      ),
      play26 = Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "3.1.3" % Test
      ),
      play27 = Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "4.0.3" % Test
      ),
      play28 = Seq(
        "org.scalatestplus.play" %% "scalatestplus-play" % "5.1.0" % Test
      )
    )
}

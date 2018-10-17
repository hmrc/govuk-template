import uk.gov.hmrc.playcrosscompilation.AbstractPlayCrossCompilation
import uk.gov.hmrc.playcrosscompilation.PlayVersion.{Play25, Play26}

object PlayCrossCompilation extends AbstractPlayCrossCompilation(defaultPlayVersion = Play25) {

  lazy val playLibVersion: String = PlayCrossCompilation.playVersion match {
    case Play25 => "2.5.12"
    case Play26 => "2.6.20"
  }
}

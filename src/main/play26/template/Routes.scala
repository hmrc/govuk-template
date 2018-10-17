package template

import controllers.Assets
import javax.inject.{Inject, Singleton}
import play.api.routing.SimpleRouter

@Singleton
class Routes @Inject() (assets: Assets) extends SimpleRouter {

  import play.api.routing._
  import play.api.routing.sird._

  override def routes: Router.Routes = Router.from {
    case GET(p"/assets/$file*") =>
      assets.versioned(path = "/public", file)
  }.routes
}


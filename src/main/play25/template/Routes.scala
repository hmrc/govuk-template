/*
 * Copyright 2018 HM Revenue & Customs
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package template

import controllers.Assets
import javax.inject.{Inject, Singleton}
import play.api.routing.SimpleRouter

@Singleton
class Routes @Inject() () extends SimpleRouter {

  import play.api.routing._
  import play.api.routing.sird._

  override def routes: Router.Routes = Router.from {
    case GET(p"/assets/$file*") =>
      Assets.versioned(path = "/public", file)
  }.routes
}

@deprecated("Use DI")
object Routes extends Routes()


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

import java.io.File

import org.scalatest.{Matchers, WordSpec}

import scala.io.Source

class EnsureTemplatesInSync extends WordSpec with Matchers {

  "Templates for play 2.5 and 2.6 should be in sync" in {
     val govUkTemplate25 = loadTemplate("play-25")("govuk_template.scala.html")
     val govUkTemplate26 = loadTemplate("play-26")("GovUkTemplate.scala.html")

     govUkTemplate25 shouldBe govUkTemplate26
  }

  private def loadTemplate(playDir: String)(templateName: String): String = {
    val userDir = System.getProperty("user.dir")
    val path = s"$userDir/src/main/$playDir/twirl/views/layouts/$templateName"
    val lines = Source.fromFile(new File(path)).getLines()
    val templateBeginning = "@("
    val contents = lines.dropWhile(l => !l.startsWith(templateBeginning)).mkString
    contents
  }

}

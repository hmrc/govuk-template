/*
 * Copyright 2019 HM Revenue & Customs
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

package uk.gov.hmrc.govuktemplate.test

import org.scalatest.{Matchers, WordSpec}
import org.scalatestplus.play.guice.GuiceOneAppPerSuite
import play.api.i18n.{Lang, Messages, MessagesApi}
import play.twirl.api.Html
import views.html.layouts.govuk_template
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import uk.gov.hmrc.govuktemplate.test.TestConstants._

class LanguageSettingSpec extends WordSpec with Matchers with GuiceOneAppPerSuite {

  val messagesApi = app.injector.instanceOf[MessagesApi]


  implicit class GovUkTemplateDoc (doc: Document) {
    def htmlTag: Elements = doc.select("html")
    def skipLink: Elements = doc.select("#skip-link")
    def logo: Elements = doc.select("#logo")
    def cookieMessage: Elements = doc.select("#cookie-info-message")
    def cookieLink: Elements = doc.select("#cookie-info-link")
    def oglLogo: Elements = doc.select("#ogl-logo")
    def oglMessage: Elements = doc.select("#ogl-message")
    def crownCopyright: Elements = doc.select("[class=copyright]")
  }

  def langMessages(langCode: String): Messages = new Messages(lang = Lang(langCode), messagesApi)

  def langView(langCode: String, title: Option[String] = Some("")): Html = {
    implicit val messages = langMessages(langCode)

    govuk_template(
      title = title,
      bodyClasses = None
    )(
      head = Html("head"),
      bodyEnd = Html("bodyEnd"),
      insideHeader = Html("insideHeader"),
      afterHeader = Html("afterHeader"),
      footerTop = Html("footerTop"),
      footerLinks = Some(Html("")),
      nav = false
    )(
      content = Html("Content")
    )
  }

  "The Gov UK template" when {
    "The language is English" should {
      "Set the lang attribute to 'en'" in {
        val doc = Jsoup.parse(langView(englishLangCode).body)
        doc.htmlTag.attr("lang") shouldBe englishLangCode
      }

      import DefaultEnglishContent._

      "Show default English content for the title if no title is provided" in {
        val doc = Jsoup.parse(langView(langCode = englishLangCode, title = None).body)
        doc.title shouldBe title
      }
      "Show default English content in the header and footer" in {
        val doc = Jsoup.parse(langView(englishLangCode).body)

        doc.skipLink.text() shouldBe skipLink
        doc.logo.attr("title") shouldBe homepageAlt
        doc.cookieMessage.text() should include (cookieMessage)
        doc.cookieLink.text() shouldBe cookieLink
        doc.oglLogo.attr("alt") shouldBe oglAlt
        doc.oglMessage.text() shouldBe oglMessage
        doc.crownCopyright.text() should include (crownCopyright)
      }
    }

    "The language is Welsh" should {
      "Set the lang attribute to 'cy'" in {
        val doc = Jsoup.parse(langView(welshLangCode).body)
        doc.select("html").attr("lang") shouldBe (welshLangCode)
      }

      import DefaultWelshContent._

      "Show default Welsh content for the title if no title is provided" in {
        val doc = Jsoup.parse(langView(langCode = welshLangCode, title = None).body)
        doc.select("title").text() shouldBe title
      }
      "Show default Welsh content in the header and footer" in {
        val doc = Jsoup.parse(langView(welshLangCode).body)

        doc.skipLink.text() shouldBe skipLink
        doc.logo.attr("title") shouldBe homepageAlt
        doc.cookieMessage.text() should include (cookieMessage)
        doc.cookieLink.text() shouldBe cookieLink
        doc.oglLogo.attr("alt") shouldBe oglAlt
        doc.oglMessage.text() shouldBe oglMessage
        doc.crownCopyright.text() should include (crownCopyright)
      }
    }
  }

}

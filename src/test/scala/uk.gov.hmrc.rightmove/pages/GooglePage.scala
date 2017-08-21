package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

object GooglePage extends BasePage {

//  override val url = ""

  def navigateToGoogle(): Unit = go to "https://www.google.co.uk"
}

package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 17/08/17.
  */
object RightMoveLandingPage extends BasePage{

  def navigateToRightMoveLandingPage(): Unit = go to "http://www.rightmove.co.uk/"

  def clickOnSignInButton = click on id("sign-in")

}

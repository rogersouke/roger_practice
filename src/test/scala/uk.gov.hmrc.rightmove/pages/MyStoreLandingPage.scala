package uk.gov.hmrc.rightmove.pages
import uk.gov.hmrc.rightmove.pages.generic.BasePage


object MyStoreLandingPage extends BasePage{

  def navigateToMyStoreLandingPage(): Unit = go to "http://automationpractice.com/index.php"

  def clickOnSignInLink = click on cssSelector(".login")

}

package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 23/08/17.
  */
object MystoreCreateAccountPage extends BasePage{

  def enterEmailAddress(): Unit = textField(id("email_create")).value = "roger@test.com"
  def clickOnCreateAccount = click on id("SubmitCreate")

}

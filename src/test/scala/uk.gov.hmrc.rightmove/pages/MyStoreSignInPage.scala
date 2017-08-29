package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 27/08/17.
  */
object MyStoreSignInPage extends BasePage {

  def navigateToMyStoreSignInPage(): Unit = go to("http://automationpractice.com/index.php?controller=authentication&back=my-account")

  def enterMyStoreEmailAndPasswd(email: String, passwd: String): Unit = {
    enterEmail(email)
    enterPassword(passwd)
  }

  def enterEmail(email: String): Unit = textField(id("email")).value = email
  def enterPassword(passwd: String): Unit = pwdField(id("passwd")).value = passwd

  def clickOnSignIn = click on id ("SubmitLogin")

}



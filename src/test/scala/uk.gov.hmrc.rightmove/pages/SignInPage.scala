package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 17/08/17.
  */
object SignInPage extends BasePage {


  def enterDetails(email: String, password: String): Unit = {
    enterEmail(email)
    enterPassword(password)
  }

  def enterEmail(email: String): Unit = textField(id("login-email")).value = email

  def enterPassword(password: String): Unit = pwdField(id("login-password")).value = password

}

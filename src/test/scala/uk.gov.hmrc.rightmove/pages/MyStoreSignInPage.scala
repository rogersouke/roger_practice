package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 27/08/17.
  */
object MyStoreSignInPage extends BasePage {

  def enterMyStoreEmailAndPasswd(email: String, passwd: String): Unit = {
    enterEmail(email)
    enterPassword(passwd)
  }

  def enterEmail(email: String): Unit = textField(id("email")).value = email
  def enterPassword(passwd: String): Unit = pwdField(id("passwd")).value = passwd
}

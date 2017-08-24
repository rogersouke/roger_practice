package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

object RightMoveRegistrationPage extends BasePage {



  def createAccountDetails(title: String, firstName: String, lastName: String): Unit = {

    enterTitle(title)
    enterFirstName(firstName)
    enterLasttName(lastName)
  }

  def enterTitle(title: String): Unit = textField(id("registration-title")).value = title

  def enterFirstName(firstName: String): Unit = textField(id("registration-firstName")).value = firstName

  def enterLasttName(lastName: String): Unit = textField(id("registration-lastName")).value = lastName

  def clickOnCreateAccountLink = click on id("createAccountLink")




  def enterEmailAndPassword(email: String, password1: String, password2: String): Unit = {
    enterEmail(email)
    enterPassword1(password1)
    enterPassword2(password2)

  }

  def enterEmail(email: String): Unit = textField(id("registration-email")).value = email

  def enterPassword1(password1: String): Unit = pwdField(id("registration-password1")).value = password1

  def enterPassword2(password2: String): Unit = pwdField(id("registration-password2")).value = password2

  def clearCheckBox = checkbox(id("consentForContact")).clear()

  def clickOnCreateAccount = click on id("create-account-button")



}

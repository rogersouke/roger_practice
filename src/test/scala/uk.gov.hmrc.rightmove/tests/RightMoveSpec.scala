package uk.gov.hmrc.rightmove.tests

import uk.gov.hmrc.rightmove.pages.{GooglePage, RegistrationPage, RightMoveLandingPage, SignInPage}


class RightMoveSpec extends BaseFeatureSpec {

  feature("Some feature") {
    scenario("Some scenario") {
      Given("I am on the Google home page")
      GooglePage.navigateToGoogle()
    }
    scenario("Search property") {

      Given("I am on the Rightmove Landing page")
      RightMoveLandingPage.navigateToRightMoveLandingPage()
      RightMoveLandingPage.clickOnSignInButton

      When("We enter our email and password")
      SignInPage.enterDetails("123", "343556")

    }

    scenario("Register account") {
      Given(/*"I want to register an account"*/ "\"This is a different text\"")
      RightMoveLandingPage.navigateToRightMoveLandingPage()
      RightMoveLandingPage.clickOnSignInButton

      When("User wants to create account")
      RegistrationPage.clickOnCreateAccountLink

      And("we enter our details")
      RegistrationPage.createAccountDetails("Mr", "Freddy", "Brody")
      RegistrationPage.enterEmailAndPassword("jessie@test.com", "victoria123", "victoria123")
      RegistrationPage.clickOnCheckbox
      RegistrationPage.clickOnCreateAccount


    }
  }
}

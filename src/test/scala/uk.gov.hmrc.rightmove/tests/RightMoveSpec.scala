package uk.gov.hmrc.rightmove.tests

import uk.gov.hmrc.rightmove.pages.{GooglePage, RightMoveRegistrationPage, RightMoveLandingPage, SignInPage}


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
      Given("I want to register an account")
      RightMoveLandingPage.navigateToRightMoveLandingPage()
      RightMoveLandingPage.clickOnSignInButton

      When("User wants to create account")
      RightMoveRegistrationPage.clickOnCreateAccountLink
      RightMoveRegistrationPage.assertPageTitle()

      And("we enter our details")
      RightMoveRegistrationPage.createAccountDetails("Mr", "Freddy", "Brody")
      RightMoveRegistrationPage.enterEmailAndPassword("umar@test.com", "victoria123", "victoria123")
      RightMoveRegistrationPage.clearCheckBox
//      RightMoveRegistrationPage.clickOnCreateAccount


    }
  }
}

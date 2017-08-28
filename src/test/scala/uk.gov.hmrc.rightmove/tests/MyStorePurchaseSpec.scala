package uk.gov.hmrc.rightmove.tests

import uk.gov.hmrc.rightmove.pages.MyStoreSignInPage

/**
  * Created by roger on 27/08/17.
  */
class MyStorePurchaseSpec extends BaseFeatureSpec {

  feature("Registered User Makes a purchase and checks out") {

    scenario(" A registered User purchases a shirt") {

      Given("User wants to create a new customer account")
      MyStoreSignInPage.navigateToMyStoreSignInPage()
      MyStoreSignInPage.clickOnSignIn

//      When("User enter their email and create a new account")
//      MyStoreSignInPage.enterMyStoreEmailAndPasswd("pogba@test.com", "victoria123")
//      MyStoreSignInPage.clickOnSignIn
//
//      And("Selects shirt")
//      And("updates their basket")
//      Then("user issss")
    }
  }

}

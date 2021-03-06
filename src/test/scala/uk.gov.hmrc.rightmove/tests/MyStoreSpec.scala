package uk.gov.hmrc.rightmove.tests

import uk.gov.hmrc.rightmove.pages.{MyStoreLandingPage, MyStoreRegistrationPage, MystoreCreateAccountPage}
import uk.gov.hmrc.rightmove.pages._


/**
  * Created by roger on 23/08/17.
  */
class MyStoreSpec extends BaseFeatureSpec{

  feature("To test new customer's subscription and purchasing journey") {


    scenario("New user creates an account") {

      Given("User wants to create a new customer account")
      MyStoreLandingPage.navigateToMyStoreLandingPage()
      MyStoreLandingPage.clickOnSignInLink

      When("User enter their email and create a new account")
      MystoreCreateAccountPage.enterEmailAddress()
      MystoreCreateAccountPage.clickOnCreateAccount

      And("User fills in the registration form")
      eventually(MyStoreRegistrationPage.selectTitle())
      MyStoreRegistrationPage.enterNameAndEmailDetails("roger", "doni", "roger@test.com", "victoria123")
      MyStoreRegistrationPage.dateOfBirthDetails("24", "8", "2017")
      MyStoreRegistrationPage.enterAddressDetails("5 barry rd", "chicago", "13", "BN4 2ER", "21")
      MyStoreRegistrationPage.enterMobilePhoneNumberAndAliasAddress("01111122390", "24 Kobe rd")

      Then("Then we assert we are on Create an Account Page")
      MyStoreRegistrationPage.assertRegistrationPageTitle()

      And("clicks on register account")
//      MyStoreRegistrationPage.clickOnRegisterButton

    }

    scenario("Asserting colours"){

      Given("Given User is already registered and wants to login")
      MyStoreLandingPage.navigateToMyStoreLandingPage()
      MyStoreLandingPage.clickOnSignInLink

      When("User enters valid account details")
      MyStoreSignInPage.enterMyStoreEmailAndPasswd("pogba@test.com", "victoria123")
      MyStoreSignInPage.clickOnSignIn

      And("And user selects catrgory")
      MyStoreAccountPage.clickOnCategory()
      MyStoreCategoryPage.clickOnQuickView
      MyStoreCategoryPage.assertColorIsBlue
    }


    scenario("Pogba contacts customer service and attaches a query file/screenshot.") {
      Given("User is already registered")
      MyStoreLandingPage.navigateToMyStoreLandingPage()
      MyStoreLandingPage.clickOnSignInLink

      When("User enters valid account details")
      MyStoreSignInPage.enterMyStoreEmailAndPasswd("pogba@test.com", "victoria123")
      MyStoreSignInPage.clickOnSignIn

      And("User selects contact us link")
      MyStoreContactUsPage.clickOnContactUsLink
      MyStoreContactUsPage.selectSubjectHeading("Customer service")
      MyStoreContactUsPage.enterComment("Not very happy with my last order please can you rectify and resend the right order now?")
      MyStoreContactUsPage.uploadQueryFile("queryFile")
      MyStoreContactUsPage.clickOnSend
    }


    scenario("A registered User purchases a shirt") {

      Given("User wants to create a new customer account")
      MyStoreLandingPage.navigateToMyStoreLandingPage()
      MyStoreLandingPage.clickOnSignInLink

      When("User enter their email and create a new account")
      MyStoreSignInPage.enterMyStoreEmailAndPasswd("pogba@test.com", "victoria123")
      MyStoreSignInPage.clickOnSignIn

      And("Selects shirt")
      MyStoreAccountPage.assertPageHeader()
      MyStoreAccountPage.clickOnCategory()

      And("updates their basket")
      MyStoreCategoryPage.chooseCategory()
      MyStoreCategoryPage.addTshirtToCart()
      Thread.sleep(3000L)
      MyStoreProductPage.switchToPop()

      And("We proceed to checkout")
      MyStoreOrderSummaryPage.clickOnProceed

      And("We enter comment and proceed")
      MyStoreAddressConfirmationPage.enterComment("Please I need 50% discount on my next purchase")
      MyStoreAddressConfirmationPage.clickOnProceedButton

      And("We confirm shipping details")
      MystoreShippingConfirmationPage.radioButtonGroup()
      MystoreShippingConfirmationPage.checkBox()
      MystoreShippingConfirmationPage.clickOnProceedToCheckOut

      Then("We assert Page title is as expected")
      MyStorePaymentPage.assertPageTitle()
      MyStorePaymentPage.clickOnBankWire

    }
  }

}

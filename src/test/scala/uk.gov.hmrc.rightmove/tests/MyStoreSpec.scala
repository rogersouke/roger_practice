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

//      And("clicks on register account")
//      MyStoreRegistrationPage.clickOnRegisterButton




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


      MyStoreProductPage.switchToPop()

//      driver.switchTo().frame("layer_cart")
//      driver.switchTo().activeElement()
//      driver.findElement(By.cssSelector(".layer_cart_cart.col-xs-12.col-md-6")).click()
//      MyStoreProductPage.switchToPopUpPage()
//      MyStoreProductPage.clickOnProceed()

      Then("")

      Thread.sleep(3000L)

      MyStoreProductPage.switchToPop()

      And("We proceed to checkout")
      MyStoreOrderSummaryPage.clickOnProceed
      MyStoreAddressConfirmationPage.clickOnProceedButton

      And("We confirm shipping details")
      MystoreShippingConfirmationPage.radioButtonGroup()
      MystoreShippingConfirmationPage.checkBox()
      MystoreShippingConfirmationPage.clickOnProceedToCheckOut

      Then("We assert Page title is as expected")
      MyStorePaymentPage.assertPageTitle()

    }
  }

}

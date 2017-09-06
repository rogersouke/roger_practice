package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 06/09/17.
  */
object MyStorePaymentPage extends BasePage{

  def assertPageTitle(): Unit = {
    val pageTitle = find(tagName("h1")).get.text
    val expectedPageTitleText: String = "PLEASE CHOOSE YOUR PAYMENT METHOD"
    pageTitle shouldBe expectedPageTitleText
  }

}

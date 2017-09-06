package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 06/09/17.
  */
object MyStoreOrderSummaryPage extends BasePage{

  def clickOnProceed = click on cssSelector(".button.btn.btn-default.standard-checkout.button-medium>span")
}

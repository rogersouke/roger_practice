package uk.gov.hmrc.rightmove.pages

import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 28/08/17.
  */
object MyStoreProductPage extends BasePage {

  def clickOnProceed(): Unit = click on cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a")

  def newSwitchPage(): Unit = driver.switchTo().frame("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6")

  def switchToPopUpPage(): Unit = driver.getWindowHandlesgir


}

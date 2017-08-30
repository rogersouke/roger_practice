package uk.gov.hmrc.rightmove.pages

import org.openqa.selenium.By
import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 28/08/17.
  */
object MyStoreProductPage extends BasePage {

  def clickOnProceed(): Unit = click on cssSelector(".btn.btn-default.button.button-medium>span")

  def newSwitchPage(): Unit = driver.switchTo().frame("layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6")

  def switchToPopUpPage(): Unit = driver.getWindowHandles

  def switchToPop(): Unit = {
    val parentWindow = driver.getWindowHandle
    val windowHandles = driver.getWindowHandles
    val iterator = windowHandles.iterator
    while (iterator.hasNext) {
      val handle = iterator.next
      if (handle.contains(parentWindow)) {

        // Switch to popup and close it
        driver.switchTo.window(handle)
        // Perform required action in popup
        driver.findElement(By.cssSelector("#layer_cart > div.clearfix > div.layer_cart_cart.col-xs-12.col-md-6 > div.button-container > a > span")).click()
      }
    }
    // Switching back to parent window
    driver.switchTo.window(parentWindow)
  }
}

package uk.gov.hmrc.rightmove.pages

import org.openqa.selenium.By
import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 28/08/17.
  */
object MyStoreProductPage extends BasePage {

  def clickOnProceed(): Unit = driver.findElement(By.partialLinkText("Proceed to checkout")).click()


}

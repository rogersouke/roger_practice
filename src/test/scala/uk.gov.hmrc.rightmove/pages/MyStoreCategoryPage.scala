package uk.gov.hmrc.rightmove.pages

import org.openqa.selenium.By
import uk.gov.hmrc.rightmove.pages.generic.BasePage

/**
  * Created by roger on 28/08/17.
  */
object MyStoreCategoryPage extends BasePage {

  def chooseCategory(): Unit = driver.findElement(By.partialLinkText("Faded Short Sleeve T-shirt")).click()

  def addTshirtToCart(): Unit = driver.findElement(By.xpath(".//*[@id='add_to_cart']/button")).click()


}

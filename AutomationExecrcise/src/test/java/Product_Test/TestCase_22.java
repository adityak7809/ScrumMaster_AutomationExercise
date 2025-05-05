package Product_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_22 extends BaseConfig {

	@Test
	public void  Add_to_cart_from_Recommended_items() throws InterruptedException {
		
		
		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Scroll to bottom of page
		WebElement recommendedItems = driver.findElement(By.xpath("//h2[text()='recommended items']"));
		js.executeScript("arguments[0].scrollIntoView(true)", recommendedItems);
		System.out.println("Scrolled to bottom of page");

		// 4. Verify 'RECOMMENDED ITEMS' are visible

		if (recommendedItems.isDisplayed()) {
			System.out.println("'RECOMMENDED ITEMS' section is visible");
		} else {
			System.out.println("'RECOMMENDED ITEMS' section is not visible");
		}

		// 5. Click on 'Add To Cart' on Recommended product (first product)
		WebElement addToCartButton = driver.findElement(By.xpath("//div[@class='recommended_items']//a[@data-product-id='1']"));
		String productName = driver.findElement(By.xpath("//p[text()='Blue Top']")).getText();

		js.executeScript("arguments[0].click();", addToCartButton);
		System.out.println("Clicked 'Add To Cart' for product: " + productName);

		// 6. Click on 'View Cart' button
		Thread.sleep(1000);
		WebElement viewCart = driver.findElement(By.xpath("//u[text()='View Cart']"));
		js.executeScript("arguments[0].click();", viewCart);
		System.out.println("Clicked 'View Cart' button");

		// 7. Verify that product is displayed in cart page
		WebElement cartProduct = driver.findElement(By.xpath("//a[text()='" + productName + "']"));
		if (cartProduct.isDisplayed()) {
			System.out.println("Product '" + productName + "' is displayed in cart page");
		} else {
			System.out.println("Product is not displayed in cart page");
		}


	}
}
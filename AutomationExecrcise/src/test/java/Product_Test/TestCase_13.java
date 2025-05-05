package Product_Test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_13 extends BaseConfig {

	@Test
	public void  Verify_Product_quantity_in_Cart() {

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// 3. Verify that home page is visible successfully
		String actualPageTitle = driver.getTitle();
		String expectedPageTitle = "Automation Exercise";
		if (actualPageTitle.equals(expectedPageTitle)) {
			System.out.println("Home page is visible successfully");
		} else {
			System.out.println("Home page is not displayed");
		}

		// 4. Click 'View Product' for any product on home page
		WebElement viewProductLink = driver.findElement(By.xpath("//a[@href='/product_details/1']"));
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true)", viewProductLink);
		viewProductLink.click();

		// 5. Verify product detail is opened
		if (driver.getTitle().equals("Automation Exercise - Product Details")) {
			System.out.println("Product details page is displayed");
		} else {
			System.out.println("Product details page is not displayed");
		}

		// 6. Increase quantity to 4
		String desiredQuantity = "4";
		WebElement quantityInput = driver.findElement(By.id("quantity"));
		quantityInput.clear();
		quantityInput.sendKeys(desiredQuantity);

		// 7. Click 'Add to cart' button
		driver.findElement(By.xpath("//button[@type='button']")).click();

		// 8. Click 'View Cart' button
		WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//u[text()='View Cart']")));
		viewCartButton.click();

		// 9. Verify that product is displayed in cart page with exact quantity
		WebElement quantityDisplay = driver.findElement(By.xpath("//button[@class='disabled']"));
		if (quantityDisplay.getText().equals(desiredQuantity)) {
			System.out.println("Product quantity in cart matches expected quantity");
		} else {
			System.out.println("Product quantity in cart does not match expected quantity");
		}


	}
}
package Product_Test;

import java.time.Duration;
import java.util.List;

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
public class TestCase_12 extends BaseConfig {

	@Test
	public void Add_Products_in_Cart() {

		// 1. Launch browser- Script in BaseConfig
		
		//Explicit Wait
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that home page is visible successfully
		String actualPageTitle = driver.getTitle();
		String expectedPageTitle = "Automation Exercise";
		if (actualPageTitle.equals(expectedPageTitle)) {
			System.out.println("Home page is visible successfully");
		} else {
			System.out.println("Home page is not displayed");
		}

		// 4. Click 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();

		// 5. Hover over first product and click 'Add to cart'
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
		WebElement firstProduct = driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", firstProduct);
		firstProduct.click();

		// 6. Click 'Continue Shopping' button
		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//button[text()='Continue Shopping']")));
		continueShoppingButton.click();

		// 7. Hover over second product and click 'Add to cart'
		WebElement secondProduct = driver.findElement(By.xpath("(//a[@data-product-id='2'])[1]"));
		javascriptExecutor.executeScript("arguments[0].scrollIntoView(true)", secondProduct);
		secondProduct.click();

		// 8. Click 'View Cart' button
		WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(
				By.xpath("//u[text()='View Cart']")));
		viewCartButton.click();

		// 9. Verify both products are added to Cart
		List<WebElement> cartProducts = driver.findElements(By.xpath("//tbody/tr"));
		if (cartProducts.size() == 2) {
			System.out.println("Both products are added to Cart successfully");
		} else {
			System.out.println("Both products are not added to Cart");
		}

		// 10. Verify their prices, quantity and total price
		for (int productIndex = 1; productIndex <= 2; productIndex++) {
			for (int columnIndex = 3; columnIndex <= 5; columnIndex++) {
				WebElement productDetail = driver.findElement(
						By.xpath("//tbody/tr[" + productIndex + "]/td[" + columnIndex + "]"));

				if (columnIndex == 3) {
					System.out.println("Product " + productIndex + " Price: " + productDetail.getText());
				} else if (columnIndex == 4) {
					System.out.println("Product " + productIndex + " Quantity: " + productDetail.getText());
				} else if (columnIndex == 5) {
					System.out.println("Product " + productIndex + " Total Price: " + productDetail.getText());
				}
			}
		}

	}
}
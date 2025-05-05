package Product_Test;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_21 extends BaseConfig {

	@Test
	public void Add_review_on_product() {

		//Generate random email
		Random random=new Random();
		int randomNum1=random.nextInt(1000);
		int randomNum2=random.nextInt(1000);
		String email="abc"+randomNum1+"xyz"+randomNum2+"@gmail.com";

		// Test data
		String reviewerName = "Test User"+randomNum1;
		String reviewerEmail = email;
		String reviewText = "This is an excellent product! Highly recommended.";

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Click on 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		System.out.println("Clicked on Products button");

		// 4. Verify user is navigated to ALL PRODUCTS page successfully
		WebElement allProductsTitle = driver.findElement(By.xpath("//h2[text()='All Products']"));
		if(allProductsTitle.isDisplayed()) {
			System.out.println("Successfully navigated to ALL PRODUCTS page");
		} else {
			System.out.println("Failed to navigate to ALL PRODUCTS page");
		}

		// 5. Click on 'View Product' button (first product)
		WebElement viewProduct= driver.findElement(By.xpath("(//a[text()='View Product'])[1]"));
		js.executeScript("arguments[0].click();", viewProduct);
		System.out.println("Clicked on View Product button");

		// 6. Verify 'Write Your Review' is visible
		WebElement reviewSection = driver.findElement(By.xpath("//a[text()='Write Your Review']"));
		if(reviewSection.isDisplayed()) {
			System.out.println("'Write Your Review' section is visible");

			// Scroll to the review section (simple JavaScript scroll)
			js.executeScript("arguments[0].scrollIntoView(true);", reviewSection);

			// 7. Enter name, email and review
			driver.findElement(By.id("name")).sendKeys(reviewerName);
			driver.findElement(By.id("email")).sendKeys(reviewerEmail);
			driver.findElement(By.id("review")).sendKeys(reviewText);
			System.out.println("Entered review details");

			// 8. Click 'Submit' button
			driver.findElement(By.id("button-review")).click();
			System.out.println("Clicked Submit button");

			// 9. Verify success message 'Thank you for your review.'
			WebElement successMessage = driver.findElement(By.xpath("//div[contains(@class,'alert-success')]"));
			if(successMessage.isDisplayed() && successMessage.getText().contains("Thank you for your review.")) 
			{
				System.out.println("Success message verified: " + successMessage.getText());
			} 
			else 
			{
				System.out.println("Review submission failed or success message not found");
			}
		} 
		else 
		{
			System.out.println("Review section not available for this product");
		}


	}
}
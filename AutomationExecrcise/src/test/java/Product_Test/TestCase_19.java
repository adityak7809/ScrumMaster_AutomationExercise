package Product_Test;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_19 extends BaseConfig {

	@Test
	public void View_and_Cart_Brand_Products() {

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Click on 'Products' button
		driver.findElement(By.xpath("//a[@href='/products']")).click();
		System.out.println("Clicked on Products button");

		// 4. Verify that Brands are visible on left side bar
		WebElement brandsSection = driver.findElement(By.xpath("//div[@class='left-sidebar']//h2[text()='Brands']"));
		if(brandsSection.isDisplayed()) {
			System.out.println("Brands section is visible on left side bar");
		} else {
			System.out.println("Brands section is not visible");
		}

		// Get all brand links
		List<WebElement> brandLinks = driver.findElements(By.xpath("//div[@class='brands_products']//a"));

		// 5. Click on any brand name (first brand)
		if(brandLinks.size() > 0)
		{
			String firstBrandName = brandLinks.get(0).getText();
			js.executeScript("arguments[0].click();", brandLinks.get(0));
			System.out.println("Clicked on brand: " + firstBrandName);

			// 6. Verify that user is navigated to brand page and brand products are displayed	
			WebElement brandProducts = driver.findElement(By.xpath("//h2[contains(@class,'title')]"));
			if(brandProducts.isDisplayed()) 
			{
				System.out.println("Successfully navigated to " + firstBrandName + " brand page");

				List<WebElement> products = driver.findElements(By.xpath("//div[@class='features_items']/div[contains(@class,'col-sm-4')]"));
				if(products.size() > 0) 
				{
					System.out.println("Brand products are displayed. Count: " + products.size());
				}
				else
				{
					System.out.println("No products found for this brand");
				}
			}
			else 
			{
				System.out.println("Failed to navigate to brand page");
			}

			// 7. On left side bar, click on any other brand link (second brand)
			brandLinks = driver.findElements(By.xpath("//div[@class='brands_products']//a"));
			if(brandLinks.size() > 1) 
			{
				String secondBrandName = brandLinks.get(1).getText();
				js.executeScript("arguments[0].click();", brandLinks.get(1));
				System.out.println("Clicked on another brand: " + secondBrandName);

				// 8. Verify that user is navigated to that brand page and can see products
				WebElement secondBrandProducts = driver.findElement(By.xpath("//h2[contains(@class,'title')]"));
				if(secondBrandProducts.isDisplayed())
				{
					System.out.println("Successfully navigated to " + secondBrandName + " brand page");

					List<WebElement> secondProducts = driver.findElements(By.xpath("//div[@class='features_items']/div[contains(@class,'col-sm-4')]"));
					if(secondProducts.size() > 0) 
					{
						System.out.println("Brand products are displayed. Count: " + secondProducts.size());
					} 
					else 
					{
						System.out.println("No products found for this brand");
					}
				}
				else
				{
					System.out.println("Failed to navigate to second brand page");
				}

			}
		}
		else 
		{
			System.out.println("No brands found");
		}



	}
}
package Product_Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_18 extends BaseConfig {

	@Test
	public void ViewCategory_Products() throws InterruptedException {

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that categories are visible on left side bar
		WebElement categoriesSection = driver.findElement(By.xpath("//div[@class='left-sidebar']//h2[contains(text(),'Category')]"));
		if(categoriesSection.isDisplayed()) {
			System.out.println("Categories section is visible on left side bar");
		} 
		else {
			System.out.println("Categories section is not visible");
		}

		// 4. Click on 'Women' category
		WebElement womenCategory = driver.findElement(By.xpath("//a[contains(@href, '#Women')]"));
		js.executeScript("arguments[0].click();", womenCategory);
		System.out.println("Clicked on Women category");

		// 5. Click on any category link under 'Women' category, for example: Dress
		Thread.sleep(1000);

		WebElement dressSubcategory = driver.findElement(By.xpath("//a[contains(@href, '/category_products/1') and contains(text(),'Dress')]"));
		js.executeScript("arguments[0].click();", dressSubcategory);
		System.out.println("Clicked on Dress subcategory");

		// 6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
		WebElement categoryTitle = driver.findElement(By.xpath("//h2[contains(@class, 'title')]"));
		if(categoryTitle.isDisplayed() && categoryTitle.getText().contains("WOMEN - DRESS PRODUCTS")) {
			System.out.println("Category page is displayed with correct title: " + categoryTitle.getText());
		} 
		else {
			System.out.println("Category page verification failed");
		}

		// 7. On left side bar, click on any sub-category link of 'Men' category
		// First click to expand Men category
		WebElement menCategory = driver.findElement(By.xpath("//a[contains(@href, '#Men')]"));
		js.executeScript("arguments[0].click();", menCategory);

		// Then click on a subcategory (e.g., Jeans)
		Thread.sleep(1000);

		WebElement jeansSubcategory = driver.findElement(By.xpath("//a[contains(@href, '/category_products/6') and contains(text(),'Jeans')]"));
		js.executeScript("arguments[0].click();", jeansSubcategory);
		System.out.println("Clicked on Jeans subcategory under Men");

		// 8. Verify that user is navigated to that category page
		WebElement menCategoryTitle = driver.findElement(By.xpath("//h2[contains(@class, 'title')]"));
		if(menCategoryTitle.isDisplayed()) {
			System.out.println("Successfully navigated to Men's Jeans category page: " + menCategoryTitle.getText());
		} 
		else {
			System.out.println("Navigation to Men's category page failed");
		}


	}
}
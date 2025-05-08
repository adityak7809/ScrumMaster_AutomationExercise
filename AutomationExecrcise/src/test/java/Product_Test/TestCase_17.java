package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.ProductsPage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_17 extends BaseConfig {

	@Test
	public void Remove_Products_From_Cart() throws InterruptedException, IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);

		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		

		//Explicit Wait
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that home page is visible successfully
		String expectedPageTitle = propObj.readData("homePageTitle");
		String actualPageTitle = homePageObj.getHomePageTitle(driver);

		if(actualPageTitle.equals(expectedPageTitle)) {
			Reporter.log("Home page is visible successfully",true);
		} else {
			Reporter.log("Home page is not displayed", true);
		}

		// 4. Add products to cart
		// Add first product
		WebElement firstProduct = productsPageObj.addProductToCart(0);
		jsClick(firstProduct);

		// Click Continue Shopping
		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(productsPageObj.clickContinueShoppingButtons()));
		continueShoppingButton.click();

		// Add second product
		WebElement secondProduct = productsPageObj.addProductToCart(1);
		jsClick(secondProduct);

		// Click Continue Shopping
		continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(productsPageObj.clickContinueShoppingButtons()));
		continueShoppingButton.click();

		// 5. Click 'Cart' button
		homePageObj.clickCartLink();

		// 6. Verify that cart page is displayed
		boolean shoppingCart=viewCartPageObj.isShoppingPageDisplayed();
		if(shoppingCart==true)
		{
			Reporter.log("Cart page is displayed successfully",true);
		}
		else
		{
			Reporter.log("Cart page is not displayed",true);
		}

		// 7. Click 'X' button corresponding to particular product
		// Get initial count of products in cart
		
		int initialItemCount = viewCartPageObj.getTotalProductCount();
		Reporter.log("Initial items in cart: " + initialItemCount,true);

		// Click delete button for first product (using first available delete button)
		if(initialItemCount > 0) {
			viewCartPageObj.removeItem(0);
			Thread.sleep(2000);
			Reporter.log("Item deleted successfully",true);
			
			// 8. Verify that product is removed from the cart
			
			int updatedItemCount = viewCartPageObj.getTotalProductCount();
			Reporter.log("Updated items in cart: " + updatedItemCount,true);

			if(updatedItemCount == initialItemCount - 1) {
				Reporter.log("Product was successfully removed from cart",true);
			} else {
				Reporter.log("Product was not removed from cart",true);
			}
		} else {
			Reporter.log("No products in cart to remove",true);
		}

	}
}
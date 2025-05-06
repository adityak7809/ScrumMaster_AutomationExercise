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
import PageRepository.ProductDetailsPage;
import PageRepository.ProductsPage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_13 extends BaseConfig {

	@Test
	public void  Verify_Product_quantity_in_Cart() throws IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		ProductDetailsPage productDetailsPageObj=new ProductDetailsPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);

		// 1. Launch browser- Script in BaseConfig

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		//Explicit Wait
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		// 3. Verify that home page is visible successfully
		String expectedPageTitle = propObj.readData("homePageTitle");
		String actualPageTitle = homePageObj.getHomePageTitle(driver);

		if(actualPageTitle.equals(expectedPageTitle)) {
			Reporter.log("Home page is visible successfully",true);
		} else {
			Reporter.log("Home page is not displayed", true);
		}

		// 4. Click 'View Product' for any product on home page
		WebElement viewProductLink = homePageObj.clickFirstViewProduct();
		jsScrollIntoView(viewProductLink);
		viewProductLink.click();

		// 5. Verify product detail is opened
		boolean ProductDetailsPage=productDetailsPageObj.isProductDetailsPageDisplayed(driver);
		if (ProductDetailsPage==true) {
			Reporter.log("Product details page is displayed",true);
		} else {
			Reporter.log("Product details page is not displayed",true);
		}

		// 6. Increase quantity to 4
		int desiredQuantity = 4;
		productDetailsPageObj.setQuantity(desiredQuantity);

		// 7. Click 'Add to cart' button
		productDetailsPageObj.clickAddToCart();

		// 8. Click 'View Cart' button
		WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(productsPageObj.clickViewCartButtons()));
		viewCartButton.click();

		// 9. Verify that product is displayed in cart page with exact quantity
		String productQuantity=viewCartPageObj.getProductQuantity(0);
	
		if (Integer.parseInt(productQuantity)==desiredQuantity) {
			Reporter.log("Product quantity in cart matches expected quantity",true);
		} else {
			Reporter.log("Product quantity in cart does not match expected quantity",true);
		}


	}
}
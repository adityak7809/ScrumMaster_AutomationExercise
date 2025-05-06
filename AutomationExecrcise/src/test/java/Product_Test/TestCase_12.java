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
public class TestCase_12 extends BaseConfig {

	@Test
	public void Add_Products_in_Cart() throws IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);

		// 1. Launch browser- Script in BaseConfig

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

		// 4. Click 'Products' button
		homePageObj.clickProductsLink();

		// 5. Hover over first product and click 'Add to cart'
		WebElement firstProduct = productsPageObj.addProductToCart(0);
		jsScrollIntoView(firstProduct);
		firstProduct.click();
		

		// 6. Click 'Continue Shopping' button
		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(productsPageObj.clickContinueShoppingButtons()));
		continueShoppingButton.click();

		// 7. Hover over second product and click 'Add to cart'
		WebElement secondProduct = productsPageObj.addProductToCart(1);
		jsScrollIntoView(secondProduct);
		secondProduct.click();

		// 8. Click 'View Cart' button
		WebElement viewCartButton = wait.until(ExpectedConditions.elementToBeClickable(productsPageObj.clickViewCartButtons()));
		viewCartButton.click();

		// 9. Verify both products are added to Cart
		if (viewCartPageObj.getTotalProductCount()== 2) {
			Reporter.log("Both products are added to Cart successfully",true);
		} else {
			Reporter.log("Both products are not added to Cart",true);
		}

		// 10. Verify their prices, quantity and total price
		System.out.println("");
		for (int productIndex = 0; productIndex <= 1; productIndex++) 
		{
			Reporter.log("Product Name: "+viewCartPageObj.getProductName(productIndex),true);
			Reporter.log("Price: "+viewCartPageObj.getProductPrice(productIndex),true);
			Reporter.log("Quantity: "+viewCartPageObj.getProductQuantity(productIndex),true);
			Reporter.log("Total Price: "+viewCartPageObj.getProductTotal(productIndex),true);
			System.out.println("");
		}
		
		
		
		
		
		
		
		

	}
}
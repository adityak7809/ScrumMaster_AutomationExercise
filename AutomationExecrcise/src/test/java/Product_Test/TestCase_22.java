package Product_Test;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_22 extends BaseConfig {

	@Test
	public void  Add_to_cart_from_Recommended_items() throws InterruptedException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);

		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Scroll to bottom of page
		jsScrollToBottom();
		Reporter.log("Scrolled to bottom of page",true);

		// 4. Verify 'RECOMMENDED ITEMS' are visible
		boolean recommendedItems=homePageObj.isRecommendedProductsHeaderDisplayed();
		if (recommendedItems==true) {
			Reporter.log("'RECOMMENDED ITEMS' section is visible",true);
		} else {
			Reporter.log("'RECOMMENDED ITEMS' section is not visible",true);
		}

		// 5. Click on 'Add To Cart' on Recommended product (first product)
		WebElement addToCartButton = homePageObj.addProductToCart(0);
		jsClick(addToCartButton);

		// 6. Click on 'View Cart' button
		Thread.sleep(1000);
		WebElement viewCart = homePageObj.clickViewCartButtons();
		jsClick(viewCart);

		// 7. Verify that product is displayed in cart page
		boolean cartProduct = viewCartPageObj.isProductDisplayed();
		if (cartProduct==true) {
			Reporter.log("Product '" + viewCartPageObj.getProductName(0) + "' is displayed in cart page",true);
		} else {
			Reporter.log("Product is not displayed in cart page",true);
		}


	}
}
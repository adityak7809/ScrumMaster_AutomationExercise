package Product_Test;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PageRepository.ProductsPage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_20_Test extends BaseConfig {

	@Test
	public void Search_Products_and_Verify_Cart_After_Login() throws InterruptedException, EncryptedDocumentException, IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);
		LoginPage loginPageObj=new LoginPage(driver);

		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Click on 'Products' button
		homePageObj.clickProductsLink();

		// 4. Verify user is navigated to ALL PRODUCTS page successfully
		boolean allProductsHeader = productsPageObj.isAllproductHeaderDisplayed();
		if (allProductsHeader==true) {
			Reporter.log("User is navigated to ALL PRODUCTS page successfully",true);
		} else {
			Reporter.log("User is not navigated to ALL PRODUCTS page successfully",true);
		}

		// 5. Enter product name in search input and click search button
		String searchProductName = exObj.readData("Search Product", 1, 0);
		productsPageObj.searchProduct(searchProductName);

		// 6. Verify 'SEARCHED PRODUCTS' is visible
		boolean serachedProducts = productsPageObj.isSeachProductTitleDisplayed();
		if (serachedProducts==true) {
			Reporter.log("'SEARCHED PRODUCTS' is visible",true);
		} else {
			Reporter.log("'SEARCHED PRODUCTS' is not visible",true);
		}

		// 7. Verify all the products related to search are visible
		List<WebElement> ProductsName = productsPageObj.searchedproductName();
		for(WebElement displayedProductsName:ProductsName)
		{
			String prodName=displayedProductsName.getText();
			String regex = "[" + searchProductName + "]";
			if(prodName.matches(".*" + regex + ".*"))
			{
				Reporter.log("Product related to search is visible: "+prodName,true);
			}
			else 
			{
				Reporter.log("Product related to search is not visible: "+prodName,true);
			}
		}

		// 8. Add those products to cart
		List<WebElement> addToCartButtons = productsPageObj.addAllproductTocart();
		int cartItemsBeforeLogin = addToCartButtons.size();
		for(WebElement button : addToCartButtons) 
		{
			jsClick(button);
			Thread.sleep(1000);
			productsPageObj.clickContinueShoppingButtons();
		}
		Reporter.log("Added " + addToCartButtons.size() + " products to cart",true);

		// 9. Click 'Cart' button and verify that products are visible in cart
		WebElement cartLink=homePageObj.jsClickCartLink();
		jsClick(cartLink);

		//verify that products are visible in cart
		int totalCartitems = viewCartPageObj.getTotalProductCount();

		if(totalCartitems == cartItemsBeforeLogin) 
		{
			Reporter.log("All " + totalCartitems + " products are visible in cart",true);
		} 
		else
		{
			Reporter.log("Mismatch in cart items. Expected: " + cartItemsBeforeLogin + ", Found: " + totalCartitems,true);
		}


		// 10. Click 'Signup / Login' button and submit login details
		homePageObj.clickSignupLoginLink();

		String email=propObj.readData("login_email");
		String password=propObj.readData("password");
		loginPageObj.enterEmail(email); 
		loginPageObj.enterPassword(password);

		loginPageObj.clickLoginButton();

		// 11. Again, go to Cart page
		boolean loggedInText = homePageObj.isUserLoggedIn();
		if (loggedInText==true) {
			Reporter.log("Logged in successfully",true);
			homePageObj.clickCartLink();


			// 12. Verify that those products are visible in cart after login as well
			int cartItemsAfterLogin = viewCartPageObj.getTotalProductCount();
			if(cartItemsAfterLogin == cartItemsBeforeLogin) {
				System.out.println("All " + cartItemsAfterLogin + " products remain in cart after login");
			} else {
				System.out.println("Cart items changed after login. Before: " + cartItemsBeforeLogin + ", After: " + cartItemsAfterLogin);
			}
		} else {
			Reporter.log("Logged in unsuccessfully...! Your email or password is incorrect!",true);
		}





	}
}
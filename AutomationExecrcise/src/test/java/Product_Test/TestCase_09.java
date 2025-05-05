package Product_Test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.CheckoutPage;
import PageRepository.ContactUsPage;
import PageRepository.DeleteAccountPage;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PageRepository.PaymentPage;
import PageRepository.ProductDetailsPage;
import PageRepository.ProductsPage;
import PageRepository.SignupPage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;


@Listeners(Listners_Imp.class)
public class TestCase_09 extends BaseConfig {


	// Javascript Code
	public void jsClick(WebElement element) {
		if (element == null)
		{
			throw new IllegalArgumentException("Element cannot be null");
		}
		else 
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}

	@Test
	public void Search_Product() throws IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		CheckoutPage checkoutPageObj=new CheckoutPage(driver);
		ContactUsPage contactUsPageObj=new ContactUsPage(driver);
		DeleteAccountPage deleteAccountPageObj=new DeleteAccountPage(driver);
		HomePage homePageObj=new HomePage(driver);
		LoginPage loginPageObj=new LoginPage(driver);
		PaymentPage paymentPageObj=new PaymentPage(driver);
		ProductDetailsPage productDetailsPageObj=new ProductDetailsPage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		SignupPage signupPageObj=new SignupPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);

		// 1. Launch browser- Script in BaseConfig

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that home page is visible successfully
		String expectedPageTitle = propObj.readData("homePageTitle");
		String actualPageTitle = homePageObj.getHomePageTitle(driver);
		
		if(actualPageTitle.equals(expectedPageTitle)) {
			Reporter.log("Home page is visible successfully",true);
		} else {
			Reporter.log("Home page is not displayed", true);
		}


		// 4. Click on 'Products' button
		homePageObj.clickProductsLink();

		// 5. Verify user is navigated to ALL PRODUCTS page successfully
		boolean allProductsHeader = productsPageObj.isAllproductHeaderDisplayed();
		if (allProductsHeader==true) {
			Reporter.log("User is navigated to ALL PRODUCTS page successfully",true);
		} else {
			Reporter.log("User is not navigated to ALL PRODUCTS page successfully",true);
		}

		// 6. Enter product name in search input and click search button
		String searchProductName = exObj.readData("Search Product", 1, 0);
		productsPageObj.searchProduct(searchProductName);

		// 7. Verify 'SEARCHED PRODUCTS' is visible
		boolean serachedProducts = productsPageObj.isSeachProductTitleDisplayed();
		if (serachedProducts==true) {
			Reporter.log("'SEARCHED PRODUCTS' is visible",true);
		} else {
			Reporter.log("'SEARCHED PRODUCTS' is not visible",true);
		}

		// 8. Verify all the products related to search are visible
		String displayedProductName = driver.findElement(By.xpath("(//p[contains(text(),'Blue Top')])[1]")).getText();
		if (displayedProductName.equals(searchProductName)) {
			Reporter.log("All the products related to search are visible",true);
		} else {
			Reporter.log("All the products related to search are not visible",true);
		}


	}
}
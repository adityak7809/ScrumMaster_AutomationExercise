package Product_Test;

import java.io.IOException;

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
public class TestCase_03 extends BaseConfig {

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
	public void Login_User_with_incorrect_email_and_password() throws IOException {

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

		// 4. Click on 'Signup / Login' button
		homePageObj.clickSignupLoginLink();

		// 5. Verify 'Login to your account' is visible
		boolean loginHeader = loginPageObj.isLoginPageSectionDisplayed();
		if (loginHeader==true) {
			Reporter.log("'Login to your account' is visible",true);
		} else {
			Reporter.log("'Login to your account' is not visible",true);
		}

		// 6. Enter incorrect email address and password
		String email=exObj.readData("Login Data", 1, 1);
		String password=exObj.readData("Login Data", 1, 2);
		loginPageObj.enterEmail(email); 
		loginPageObj.enterPassword(password);

		// 7. Click 'login' button
		loginPageObj.clickLoginButton();

		// 8. Verify error 'Your email or password is incorrect!' is visible
		boolean errorMessage = loginPageObj.isErrorMessageDisplayed();
		if (errorMessage==true) {
			Reporter.log("'Your email or password is incorrect!' is visible",true);
		} else {
			Reporter.log("'Your email or password is incorrect!' is not visible",true);
		}


	}
}
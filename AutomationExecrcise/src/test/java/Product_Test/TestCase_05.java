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
public class TestCase_05 extends BaseConfig {

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
	public void Register_User_with_existing_email() throws IOException {

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

		// 5. Verify 'New User Signup!' is visible
		boolean newUserSignupText = loginPageObj.isNewUserSignupSectionDisplayed();
		if(newUserSignupText==true) {
			Reporter.log("'New User Signup!' is visible",true);
		} else {
			Reporter.log("'New User Signup!' is not visible",true);
		}

		// 6. Enter name and email address
		String name=exObj.readData("Login Data", 3, 0);
		String email=exObj.readData("Login Data", 3, 1);
		loginPageObj.enterSignupName(name);
		loginPageObj.enterSignupEmail(email);

		// 7. Click 'Signup' button
		loginPageObj.clickSignupButton();

		// 8. Verify error 'Email Address already exist!' is visible
		boolean emailExistsErrorText = loginPageObj.isErrorMessage2Displayed();
		if (emailExistsErrorText==true) {
			Reporter.log("'Email Address already exist!' is visible",true);
		} else {
			Reporter.log("'Email Address already exist!' is not visible",true);
		}


	}
}
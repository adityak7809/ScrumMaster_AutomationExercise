package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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
public class TestCase_06 extends BaseConfig {

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
	public void Contact_Us_Form() throws IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

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

		// 4. Click on 'Contact Us' button
		homePageObj.clickContactUsLink();

		// 5. Verify 'GET IN TOUCH' is visible
		boolean getInTouchHeader = contactUsPageObj.getInTouchHeaderDisplayed();
		if (getInTouchHeader==true) {
			Reporter.log("'GET IN TOUCH' is visible",true);
		} else {
			Reporter.log("'GET IN TOUCH' is not visible",true);
		}

		// 6. Enter name, email, subject and message
		String name=exObj.readData("Contact Us", 1, 0);
		String email=exObj.readData("Contact Us", 1, 1);
		String subject=exObj.readData("Contact Us", 1, 2);
		String message=exObj.readData("Contact Us", 1, 3);
		contactUsPageObj.fillContactForm(name, email, subject, message);
		

		// 7. Upload file
		String filePath=exObj.readData("Contact Us", 1, 4);
		contactUsPageObj.uploadFile(filePath);

		// 8. Click 'Submit' button
		jsClick(contactUsPageObj.clickSubmit());

		// 9. Click OK button on alert
		wait.until(ExpectedConditions.alertIsPresent()).accept();

		// 10. Verify success message is visible
		boolean successMessage = contactUsPageObj.isSuccessMessageDisplayed();
		if (successMessage==true) {
			Reporter.log("'Success! Your details have been submitted successfully.' is visible",true);
		} else {
			Reporter.log("'Success! Your details have been submitted successfully.' is not visible",true);
		}

		// 11. Click 'Home' button and verify that landed to home page successfully
		contactUsPageObj.clickHomeButton();


	}
}
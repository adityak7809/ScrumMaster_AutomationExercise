package Product_Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
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
import PageRepository.DeleteAccountPage;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PageRepository.PaymentPage;
import PageRepository.ProductsPage;
import PageRepository.SignupPage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_23_Test extends BaseConfig {

	@Test
	public void Verify_address_details_in_checkout_page() throws InterruptedException, EncryptedDocumentException, IOException {



		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		DeleteAccountPage deleteAccountPageObj=new DeleteAccountPage(driver);
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		CheckoutPage checkoutPageObj=new CheckoutPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);
		LoginPage loginPageObj=new LoginPage(driver);
		SignupPage signupPageObj=new SignupPage(driver);
		PaymentPage paymentPageObj=new PaymentPage(driver);

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


		// 4. Click 'Signup / Login' button
		homePageObj.clickSignupLoginLink();

		//Generate Random mail
		Random ran=new Random();
		int num1=ran.nextInt(1000);
		int num2=ran.nextInt(1000);
		String randomMail="abc"+num1+"xyz"+num2+"@mail.com";

		//Write Data to Excel
		exObj.updateData("Create Account", 1, 1, randomMail);

		// 5. Fill all details in Signup and create account
		// Enter name and email
		String name=exObj.readData("Create Account", 1, 0);
		String email=exObj.readData("Create Account", 1, 1);
		loginPageObj.enterSignupName(name);
		loginPageObj.enterSignupEmail(email);

		loginPageObj.clickSignupButton();


		// Fill account information
		String gender=exObj.readData("Create Account", 1, 2);
		jsClick(signupPageObj.selectGender(gender)); 

		String password=exObj.readData("Create Account", 1, 3);
		signupPageObj.enterPassword(password);


		// Date of birth
		String day=exObj.readData("Create Account", 1, 4);
		String month=exObj.readData("Create Account", 1, 5);
		String year=exObj.readData("Create Account", 1, 6);
		signupPageObj.selectDateOfBirth(day, month, year);

		jsClick( signupPageObj.toggleNewsletter());
		jsClick(signupPageObj.toggleSpecialOffers());

		// Address information
		String firstName=exObj.readData("Create Account", 1, 7);
		String lastName=exObj.readData("Create Account", 1, 8);
		String company=exObj.readData("Create Account", 1, 9);
		String address1=exObj.readData("Create Account", 1, 10);
		String address2=exObj.readData("Create Account", 1, 11);
		String country=exObj.readData("Create Account", 1, 12);
		String state=exObj.readData("Create Account", 1, 13);
		String city=exObj.readData("Create Account", 1, 14);
		String zipcode=exObj.readData("Create Account", 1, 15);
		String mobile_number=exObj.readData("Create Account", 1, 16);

		signupPageObj.enterAddressInfo(firstName, lastName, company, address1, address2, country, state, city, zipcode, mobile_number);

		// Click Create Account button
		jsClick(signupPageObj.clickCreateAccount());

		// 6. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		boolean accountCreatedText = signupPageObj.isAccountCreatedSuccessfully();
		if(accountCreatedText==true) {
			Reporter.log("'ACCOUNT CREATED!' is visible",true);
			signupPageObj.clickContinue();
		} else {
			Reporter.log("'ACCOUNT CREATED!' is not visible",true);
		}

		// 7. Verify 'Logged in as username' at top
		boolean loggedInText = homePageObj.isUserLoggedIn();
		if(loggedInText==true) {
			Reporter.log("'Logged in as username' is visible",true);
		} else {
			Reporter.log("'Logged in as username' is not visible",true);
		}


		// 8. Add products to cart
		WebElement firstProduct = productsPageObj.addProductToCart(0);
		jsClick(firstProduct);

		WebElement continueShoppingButton = wait.until(ExpectedConditions.elementToBeClickable(productsPageObj.clickContinueShoppingButtons()));
		continueShoppingButton.click();

		// 9. Click 'Cart' button
		homePageObj.clickCartLink();

		// 10. Verify that cart page is displayed
		boolean shoppingCart=viewCartPageObj.isShoppingPageDisplayed();
		if(shoppingCart==true)
		{
			Reporter.log("Cart page is displayed successfully",true);
		}
		else
		{
			Reporter.log("Cart page is not displayed",true);
		}

		// 11. Click Proceed To Checkout
		viewCartPageObj.clickProceedToCheckout();

		// 12. Verify that the delivery address is same address filled at the time registration of account
		String deliveryCompany =checkoutPageObj.getDelAddress1();
		String deliveryAddress1 =checkoutPageObj.getDelAddress2();
		String deliveryAddress2 =checkoutPageObj.getDelAddress3();
		String deliveryCountry =checkoutPageObj.getDelCountry();
		String deliveryPhone =checkoutPageObj.getDelPhone();

		if ( deliveryCompany.equals(company) && 
				deliveryAddress1.equals(address1) && 
				deliveryAddress2.equals(address2) &&
				deliveryCountry.equals(country) &&
				deliveryPhone.equals(mobile_number)) {
			Reporter.log("Delivery address matches registration address",true);
		} else {
			Reporter.log("Delivery address does not match registration address",true);
		}

		// 13. Verify that the billing address is same address filled at the time registration of account
		String billingCompany =checkoutPageObj.getBilAddress1();
		String billingAddress1 =checkoutPageObj.getBilAddress2();
		String billingAddress2 =checkoutPageObj.getBilAddress3();
		String billingCountry =checkoutPageObj.getBilCountry();
		String billingPhone =checkoutPageObj.getBilPhone();

		if ( billingCompany.equals(company) && 
				billingAddress1.equals(address1) && 
				billingAddress2.equals(address2) &&
				billingCountry.equals(country) &&
				billingPhone.equals(mobile_number)) {
			Reporter.log("Billing address matches registration address",true);
		} else {
			Reporter.log("Billing address does not match registration address",true);
		}

		// 14. Click 'Delete Account' button
		homePageObj.clickOnDeleteAccount();

		// 15. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		boolean accountDeletedText = deleteAccountPageObj.isAccountDeleted();
		if(accountDeletedText==true) {
			Reporter.log("'ACCOUNT DELETED!' is visible",true);
			deleteAccountPageObj.clickContinue();
		} else {
			Reporter.log("'ACCOUNT DELETED!' is not visible",true);
		}


	}
}
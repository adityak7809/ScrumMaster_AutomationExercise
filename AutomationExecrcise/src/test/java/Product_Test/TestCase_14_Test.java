package Product_Test;

import java.io.IOException;
import java.time.Duration;
import java.util.Random;

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
public class TestCase_14_Test extends BaseConfig {

	@Test
	public void Place_Order_Register_while_Checkout() throws InterruptedException, IOException {
		
		TestCase_14_Test classObj=new TestCase_14_Test();
		Reporter.log("Executing class:- "+"#"+printClassName(classObj)+"#",true);

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

		// 7. Click Proceed To Checkout
		viewCartPageObj.clickProceedToCheckout();

		// 8. Click 'Register / Login' button
		viewCartPageObj.clickRegisterLogin();

		//Generate Random mail
		Random ran=new Random();
		int num1=ran.nextInt(1000);
		int num2=ran.nextInt(1000);
		String randomMail="abc"+num1+"xyz"+num2+"@mail.com";

		//Write Data to Excel
		exObj.updateData("Create Account", 1, 1, randomMail);

		// 9. Fill all details in Signup and create account
		String name=exObj.readData("Create Account", 1, 0);
		String email=exObj.readData("Create Account", 1, 1);
		loginPageObj.enterSignupName(name);
		loginPageObj.enterSignupEmail(email);

		loginPageObj.clickSignupButton();


		String genderData=exObj.readData("Create Account", 1, 2);
		WebElement gender = signupPageObj.selectGender(genderData);
		jsClick(gender);


		String password=exObj.readData("Create Account", 1, 3);
		signupPageObj.enterPassword(password);

		//DOB
		String day=exObj.readData("Create Account", 1, 4);
		String month=exObj.readData("Create Account", 1, 5);
		String year=exObj.readData("Create Account", 1, 6);
		signupPageObj.selectDateOfBirth(day, month, year);
		jsClick( signupPageObj.toggleNewsletter());

		jsClick(signupPageObj.toggleSpecialOffers());

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

		jsClick(signupPageObj.clickCreateAccount());

		// 10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		boolean accountCreatedText = signupPageObj.isAccountCreatedSuccessfully();
		if(accountCreatedText==true) {
			Reporter.log("'ACCOUNT CREATED!' is visible",true);
			signupPageObj.clickContinue();
		} else {
			Reporter.log("'ACCOUNT CREATED!' is not visible",true);
		}


		// 11. Verify ' Logged in as username' at top
		WebElement loggedInElement=homePageObj.getUserLoggedInElement();
		boolean loggedInText = loggedInElement.isDisplayed();
		if(loggedInText==true) {
			Reporter.log("'Logged in as username' is visible",true);
		} else {
			Reporter.log("'Logged in as username' is not visible",true);
		}

		// 12.Click 'Cart' button
		homePageObj.clickCartLink();

		// 13. Click 'Proceed To Checkout' button
		viewCartPageObj.clickProceedToCheckout();

		// 14. Verify Address Details and Review Your Order
		String deliveryFullName=checkoutPageObj.getDelFullName();
		String deliveryAddress1 =checkoutPageObj.getDelAddress1();
		String deliveryAddress2 =checkoutPageObj.getDelAddress2();
		String deliveryAddress3 =checkoutPageObj.getDelAddress3();
		String deliveryCityStatePostcode =checkoutPageObj.getDelCityStatePostcode();
		String deliveryCountry =checkoutPageObj.getDelCountry();
		String deliveryPhone =checkoutPageObj.getDelPhone();

		String billingFullName=checkoutPageObj.getBilFullName();
		String billingAddress1 =checkoutPageObj.getBilAddress1();
		String billingAddress2 =checkoutPageObj.getBilAddress2();
		String billingAddress3 =checkoutPageObj.getBilAddress3();
		String billingCityStatePostcode =checkoutPageObj.getBilCityStatePostcode();
		String billingCountry =checkoutPageObj.getBilCountry();
		String billingPhone =checkoutPageObj.getBilPhone();


		if(deliveryFullName.equals(billingFullName) && deliveryAddress1.equals(billingAddress1) 
				&& deliveryAddress2.equals(billingAddress2) && deliveryAddress3.equals(billingAddress3) 
				&& deliveryCityStatePostcode.equals(billingCityStatePostcode) 
				&& deliveryCountry.equals(billingCountry) && deliveryPhone.equals(billingPhone)) {

			Reporter.log("Address Details are successfully verified",true);
		}
		else
		{
			Reporter.log("Address Details are not verified",true);
		}

		System.out.println("");
		for (int productIndex = 0; productIndex <= 1; productIndex++) 
		{
			Reporter.log("Product Name: "+viewCartPageObj.getProductName(productIndex),true);
			Reporter.log("Price: "+viewCartPageObj.getProductPrice(productIndex),true);
			Reporter.log("Quantity: "+viewCartPageObj.getProductQuantity(productIndex),true);
			Reporter.log("Total Price: "+viewCartPageObj.getProductTotal(productIndex),true);
			System.out.println("");
		}
		Reporter.log("Order review successfully verified",true);

		// 15. Enter description in comment text area and click 'Place Order'
		String msg=exObj.readData("Product Detail", 1, 2);
		checkoutPageObj.clickCommentBox(msg);

		// 16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		String name_on_card = exObj.readData("Payment Details", 1, 0);
		String card_number = exObj.readData("Payment Details", 1, 1);
		String cvc = exObj.readData("Payment Details", 1, 2);
		String expiry_month = exObj.readData("Payment Details", 1, 3);
		String expiry_year = exObj.readData("Payment Details", 1, 4);
		paymentPageObj.enterPaymentDetails(name_on_card, card_number, cvc, expiry_month, expiry_year);

		// 17. Click 'Pay and Confirm Order' button
		WebElement comfirmOrder= paymentPageObj.clickPayAndConfirmOrder();
		jsClick(comfirmOrder);

		// 18. Verify success message 'Your order has been placed successfully!'
		Thread.sleep(1000);
		WebElement successMsgText= paymentPageObj.isOrderConfirmed();
		boolean successMsg=successMsgText.isDisplayed();
		if(successMsg==true)
		{
			Reporter.log("'Your order has been placed successfully!' verified successfully",true);
		}
		else
		{
			Reporter.log("'Your order has been placed successfully!' not verified",true);
		}

		// 19. Click 'Delete Account' button
		homePageObj.clickOnDeleteAccount();

		// 20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		boolean accountDeletedText = deleteAccountPageObj.isAccountDeleted();
		if(accountDeletedText==true) {
			Reporter.log("'ACCOUNT DELETED!' is visible",true);
			deleteAccountPageObj.clickContinue();
		} else {
			Reporter.log("'ACCOUNT DELETED!' is not visible",true);
		}

	}

}












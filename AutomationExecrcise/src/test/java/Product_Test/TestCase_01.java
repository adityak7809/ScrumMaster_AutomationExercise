package Product_Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.DeleteAccountPage;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PageRepository.SignupPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_01 extends BaseConfig {


	@Test
	public void Register_User() throws InterruptedException, EncryptedDocumentException, IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		DeleteAccountPage deleteAccountPageObj=new DeleteAccountPage(driver);
		HomePage homePageObj=new HomePage(driver);
		LoginPage loginPageObj=new LoginPage(driver);
		SignupPage signupPageObj=new SignupPage(driver);

		// 1. Launch browser- Script in BaseConfig

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig


		//3. Verify that home page is visible successfully
		String expectedPageTitle = propObj.readData("homePageTitle");
		String actualPageTitle = homePageObj.getHomePageTitle(driver);

		if(actualPageTitle.equals(expectedPageTitle)) {
			Reporter.log("Home page is visible successfully",true);
		} else {
			Reporter.log("Home page is not displayed", true);
		}

		//4. Click on 'Signup / Login' button
		homePageObj.clickSignupLoginLink();

		//5. Verify 'New User Signup!' is visible
		boolean newUserSignupText = loginPageObj.isNewUserSignupSectionDisplayed();
		if(newUserSignupText==true) {
			Reporter.log("'New User Signup!' is visible",true);
		} else {
			Reporter.log("'New User Signup!' is not visible",true);
		}

		//6. Enter name and email address
		String name=exObj.readData("Create Account", 1, 0);
		String email=exObj.readData("Create Account", 1, 1);
		loginPageObj.enterSignupName(name);
		loginPageObj.enterSignupEmail(email);

		//7. Click 'Signup' button
		loginPageObj.clickSignupButton();

		//8. Verify that 'ENTER ACCOUNT INFORMATION' is visible
		boolean accountInfoText = signupPageObj.isPageLoaded();
		if(accountInfoText==true) {
			Reporter.log("'ENTER ACCOUNT INFORMATION' is visible",true);
		} else {
			Reporter.log("'ENTER ACCOUNT INFORMATION' is not visible",true);
		}

		//9. Fill details: Title, Name, Email, Password, Date of birth
		String gender=exObj.readData("Create Account", 1, 2);
		signupPageObj.selectGender(gender);

		String password=exObj.readData("Create Account", 1, 3);
		signupPageObj.enterPassword(password);

		//DOB
		String day=exObj.readData("Create Account", 1, 4);
		String month=exObj.readData("Create Account", 1, 5);
		String year=exObj.readData("Create Account", 1, 6);
		signupPageObj.selectDateOfBirth(day, month, year);


		//10. Select checkbox 'Sign up for our newsletter!'
		jsClick( signupPageObj.toggleNewsletter());

		//11. Select checkbox 'Receive special offers from our partners!'
		jsClick(signupPageObj.toggleSpecialOffers());

		//12. Fill details: First name, Last name, Company, Address, Address2, Country, State, City, Zipcode, Mobile Number
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

		//13. Click 'Create Account button'
		jsClick(signupPageObj.clickCreateAccount());
		

		//14. Verify that 'ACCOUNT CREATED!' is visible
		boolean accountCreatedText = signupPageObj.isAccountCreatedSuccessfully();
		if(accountCreatedText==true) {
			Reporter.log("'ACCOUNT CREATED!' is visible",true);
		} else {
			Reporter.log("'ACCOUNT CREATED!' is not visible",true);
		}

		//15. Click 'Continue' button
		signupPageObj.clickContinue();

		//16. Verify that 'Logged in as username' is visible
		boolean loggedInText = homePageObj.isUserLoggedIn();
		if(loggedInText==true) {
			Reporter.log("'Logged in as username' is visible",true);
		} else {
			Reporter.log("'Logged in as username' is not visible",true);
		}

		//17. Click 'Delete Account' button
		homePageObj.clickOnDeleteAccount();

		//18. Verify that 'ACCOUNT DELETED!' is visible and click 'Continue' button
		boolean accountDeletedText = deleteAccountPageObj.isAccountDeleted();
		if(accountDeletedText==true) {
			Reporter.log("'ACCOUNT DELETED!' is visible",true);
			deleteAccountPageObj.clickContinue();
		} else {
			Reporter.log("'ACCOUNT DELETED!' is not visible",true);
		}


	}
}
package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.DeleteAccountPage;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_02 extends BaseConfig {


	@Test
	public void Login_User_with_correct_email_and_password() throws InterruptedException, IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		DeleteAccountPage deleteAccountPageObj=new DeleteAccountPage(driver);
		HomePage homePageObj=new HomePage(driver);
		LoginPage loginPageObj=new LoginPage(driver);

		// 1. Launch browser- Action in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// 2. Navigate to url 'http://automationexercise.com'- Action in BaseConfig

		// 3. Verify that home page is visible successfully
		String expectedPageTitle = propObj.readData("homePageTitle");
		String actualPageTitle = homePageObj.getHomePageTitle(driver);

		if(actualPageTitle.equals(expectedPageTitle)) {
			Reporter.log("Home page is visible successfully",true);
		} else {
			Reporter.log("Home page is not displayed", true);
		}

		//Add new valid account
		createAccount();
		
		// 4. Click on 'Signup / Login' button
		homePageObj.clickSignupLoginLink();

		// 5. Verify 'Login to your account' is visible
		boolean loginHeader = loginPageObj.isLoginPageSectionDisplayed();
		if (loginHeader==true) {
			Reporter.log("'Login to your account' is visible",true);
		} else {
			Reporter.log("'Login to your account' is not visible",true);
		}

		// 6. Enter correct email address and password
		String email=exObj.readData("Create Account", 1, 1);
		String password=exObj.readData("Create Account", 1, 3);
		loginPageObj.enterEmail(email); 
		loginPageObj.enterPassword(password);

		// 7. Click 'login' button
		loginPageObj.clickLoginButton();

		// 8. Verify that 'Logged in as username' is visible
		boolean loggedInText = homePageObj.isUserLoggedIn();
		if (loggedInText==true) {
			Reporter.log("'Logged in as username' is visible:- "+homePageObj.getLoggedInUsername(),true);
		} else {
			Reporter.log("'Logged in as username' is not visible:- "+homePageObj.getLoggedInUsername(),true);
		}

		// 9. Click 'Delete Account' button
		homePageObj.clickOnDeleteAccount();

		// 10. Verify that 'ACCOUNT DELETED!' is visible
		boolean accountDeletedText = deleteAccountPageObj.isAccountDeleted();
		if(accountDeletedText==true) {
			Reporter.log("'ACCOUNT DELETED!' is visible",true);
		} else {
			Reporter.log("'ACCOUNT DELETED!' is not visible",true);
		}


	}
}
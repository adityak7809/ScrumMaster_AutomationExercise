package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_03_Test extends BaseConfig {



	@Test
	public void Login_User_with_incorrect_email_and_password() throws IOException {
		
		TestCase_03_Test classObj=new TestCase_03_Test();
		Reporter.log("Executing class:- "+"#"+printClassName(classObj)+"#",true);

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		LoginPage loginPageObj=new LoginPage(driver);

		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
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
		String email=exObj.readData("Create Account", 1, 1);
		String password=exObj.readData("Create Account", 1, 2);
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
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
public class TestCase_05_Test extends BaseConfig {


	@Test
	public void Register_User_with_existing_email() throws IOException {
		
		TestCase_05_Test classObj=new TestCase_05_Test();
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

		// 5. Verify 'New User Signup!' is visible
		boolean newUserSignupText = loginPageObj.isNewUserSignupSectionDisplayed();
		if(newUserSignupText==true) {
			Reporter.log("'New User Signup!' is visible",true);
		} else {
			Reporter.log("'New User Signup!' is not visible",true);
		}

		// 6. Enter name and email address
		String name=propObj.readData("name");
		String email=propObj.readData("login_email");
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
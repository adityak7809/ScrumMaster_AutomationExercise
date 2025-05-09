package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.TestCasesPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_07_Test extends BaseConfig {



	@Test
	public void Verify_Test_Cases_Page() throws IOException {
		
		TestCase_07_Test classObj=new TestCase_07_Test();
		Reporter.log("Executing class:- "+"#"+printClassName(classObj)+"#",true);

		// Create Object Ref. variable
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		TestCasesPage testCasesPageObj=new TestCasesPage(driver);

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

		// 4. Click on 'Test Cases' button
		homePageObj.clicktestCasesLinkLink();

		// 5. Verify user is navigated to test cases page successfully
		boolean testCasesHeader = testCasesPageObj.isPageLoaded();
		if (testCasesHeader==true) {
			Reporter.log("User is navigated to test cases page successfully",true);
		} else {
			Reporter.log("User is not navigated to test cases page successfully",true);
		}


	}
}
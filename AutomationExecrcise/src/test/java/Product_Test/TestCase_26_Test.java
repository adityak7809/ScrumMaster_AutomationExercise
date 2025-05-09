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
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_26_Test extends BaseConfig {


	@Test
	public void Verify_Scroll_Up_without_Arrow_button_and_Scroll_Down_functionality() throws IOException, InterruptedException {
		
		TestCase_26_Test classObj=new TestCase_26_Test();
		Reporter.log("Executing class:- "+"#"+printClassName(classObj)+"#",true);

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);

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

		// 4. Scroll down page to bottom
		jsScrollToBottom();
		Reporter.log("Scrolled to bottom of page",true);
		Thread.sleep(1000);

		// 5. Verify 'SUBSCRIPTION' is visible
		boolean subscriptionHeader = homePageObj.isSubscriptionHeaderDisplayed();
		if (subscriptionHeader==true) {
			Reporter.log("'SUBSCRIPTION' text is visible",true);
		} else {
			Reporter.log("'SUBSCRIPTION' text is not visible",true);
		}

		// 6. Scroll up page to top
		jsScrollToUp();
		Reporter.log("Scrolled back to top of page",true);

		// 7. Verify that page is scrolled up and text is visible
		boolean  topText = homePageObj.istopTextDisplayed();
		if (topText==true) {
			Reporter.log("Page is scrolled up and text is visible",true);
		} else {
			Reporter.log("Page scroll up verification failed",true);
		}


	}
}
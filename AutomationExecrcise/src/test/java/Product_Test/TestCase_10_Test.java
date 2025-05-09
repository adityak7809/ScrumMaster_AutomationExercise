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
public class TestCase_10_Test extends BaseConfig {

	

	@Test
	public void Verify_Subscription_in_home_page() throws IOException {

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

		// 4. Scroll down to footer
		jsScrollToBottom();
		
		
		// 5. Verify text 'SUBSCRIPTION'
		boolean subscriptionHeader = homePageObj.isSubscriptionHeaderDisplayed();
		if (subscriptionHeader==true) {
			Reporter.log("'SUBSCRIPTION' text is visible",true);
		} else {
			Reporter.log("'SUBSCRIPTION' text is not visible",true);
		}

		// 6. Enter email address in input and click arrow button
		String subscriptionEmail = exObj.readData("Create Account", 1, 1);
		homePageObj.subscribeToNewsletter(subscriptionEmail);

		// 7. Verify success message is visible
		if (homePageObj.isSubscriptionSuccessful()==true) {
			Reporter.log("'You have been successfully subscribed!' is visible",true);
		} else {
			Reporter.log("'You have been successfully subscribed!' is not visible",true);
		}

	}
}
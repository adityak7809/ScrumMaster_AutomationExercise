package Product_Test;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_11 extends BaseConfig {




	@Test
	public void Verify_Subscription_in_Cart_page() throws EncryptedDocumentException, IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);

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

		// 4. Click 'Cart' button
		homePageObj.clickCartLink();

		// 5. Scroll down to footer
		jsScrollToBottom();

		// 6. Verify text 'SUBSCRIPTION'
		boolean subscriptionHeader = homePageObj.isSubscriptionHeaderDisplayed();
		if (subscriptionHeader==true) {
			Reporter.log("'SUBSCRIPTION' text is visible",true);
		} else {
			Reporter.log("'SUBSCRIPTION' text is not visible",true);
		}

		// 7. Enter email address in input and click arrow button
		String subscriptionEmail = exObj.readData("Login Data", 1, 1);
		homePageObj.subscribeToNewsletter(subscriptionEmail);
		
		// 8. Verify success message is visible
		if (homePageObj.isSubscriptionSuccessful()==true) {
			Reporter.log("'You have been successfully subscribed!' is visible",true);
		} else {
			Reporter.log("'You have been successfully subscribed!' is not visible",true);
		}


	}
}
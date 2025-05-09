package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.ContactUsPage;
import PageRepository.HomePage;
import PropertyUtility.ReadPropertyFile;


@Listeners(Listners_Imp.class)
public class TestCase_06_Test extends BaseConfig {

	@Test
	public void Contact_Us_Form() throws IOException {
		
		TestCase_06_Test classObj=new TestCase_06_Test();
		Reporter.log("Executing class:- "+"#"+printClassName(classObj)+"#",true);

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();
		WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(10));

		//POM Class
		ContactUsPage contactUsPageObj=new ContactUsPage(driver);
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

		// 4. Click on 'Contact Us' button
		homePageObj.clickContactUsLink();

		// 5. Verify 'GET IN TOUCH' is visible
		boolean getInTouchHeader = contactUsPageObj.getInTouchHeaderDisplayed();
		if (getInTouchHeader==true) {
			Reporter.log("'GET IN TOUCH' is visible",true);
		} else {
			Reporter.log("'GET IN TOUCH' is not visible",true);
		}

		// 6. Enter name, email, subject and message
		String name=exObj.readData("Contact Us", 1, 0);
		String email=exObj.readData("Contact Us", 1, 1);
		String subject=exObj.readData("Contact Us", 1, 2);
		String message=exObj.readData("Contact Us", 1, 3);
		contactUsPageObj.fillContactForm(name, email, subject, message);
		

		// 7. Upload file
		String filePath=exObj.readData("Contact Us", 1, 4);
		contactUsPageObj.uploadFile(filePath);

		// 8. Click 'Submit' button
		jsClick(contactUsPageObj.clickSubmit());

		// 9. Click OK button on alert
		wait.until(ExpectedConditions.alertIsPresent()).accept();

		// 10. Verify success message is visible
		boolean successMessage = contactUsPageObj.isSuccessMessageDisplayed();
		if (successMessage==true) {
			Reporter.log("'Success! Your details have been submitted successfully.' is visible",true);
		} else {
			Reporter.log("'Success! Your details have been submitted successfully.' is not visible",true);
		}

		// 11. Click 'Home' button and verify that landed to home page successfully
		contactUsPageObj.clickHomeButton();


	}
}
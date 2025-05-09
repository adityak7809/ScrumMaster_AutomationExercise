package GenericRepository;

import java.io.IOException;
import java.util.Random;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import ExcelUtility.ReadExcelFile;
import PageRepository.HomePage;
import PageRepository.LoginPage;
import PageRepository.SignupPage;
import PropertyUtility.ReadPropertyFile;


public class BaseConfig {

	//Global Declaration
	public WebDriver driver;
	public ReadPropertyFile propFileObj;
	public static WebDriver static_driver;

	@Parameters({"browser", "username"})
	@BeforeClass
	public void browserSetup( @Optional("chrome") String browser, 
		    @Optional("defaultUser") String username) throws IOException
	{

		//Step 1: Create Object for All Library
		propFileObj=new ReadPropertyFile();

		//Step 2: Launch the browser
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
			static_driver=driver;
			Reporter.log("Browser verified", true);
			Reporter.log("Opening: "+browser, true);
		}
		else if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
			static_driver=driver;
			Reporter.log("Browser verified", true);
			Reporter.log("Opening: "+browser, true);
		}
		else if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
			static_driver=driver;
			Reporter.log("Browser verified", true);
			Reporter.log("Opening: "+browser, true);
		}


		//Step 3: Fetch the URL data and Property-file
		String url=propFileObj.readData("url");


		//Step 4: Maximize the browser
		driver.manage().window().maximize();

		//Step 5: Navigate to the Application 
		driver.get(url);
		Reporter.log("Navigating to URL: "+url, true);


	}

	@Parameters({"browser", "username"})
	@AfterClass
	public void closebrowser(String browser, String username)
	{
		//Close the browser
		driver.close();
		Reporter.log("Browser closed: "+browser, true);

		Reporter.log("Test execution completed by: "+username, true);
	}

	// Javascript Code
	
	public void jsClick(WebElement element) {
		if (element == null)
		{
			throw new IllegalArgumentException("Element cannot be null");
		}
		else 
		{
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		}
	}
	
	public void jsScrollIntoView(WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true)", element);
	}

	public void jsScrollToBottom() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
	}
	
	public void jsScrollToUp() {
		((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 0)");
	}
	
	public void createAccount() throws EncryptedDocumentException, IOException
	{
		
		HomePage homePageObj=new HomePage(driver);
		LoginPage loginPageObj=new LoginPage(driver);
		SignupPage signupPageObj=new SignupPage(driver);

		
		//Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();

		homePageObj.clickSignupLoginLink();
		
		//Generate Random mail
		Random ran=new Random();
		int num1=ran.nextInt(1000);
		int num2=ran.nextInt(1000);
		String randomMail="abc"+num1+"xyz"+num2+"@mail.com";
		
		//Write Data to Excel
		exObj.updateData("Create Account", 1, 1, randomMail);
		
		//Fetch Data from Excel
		String name=exObj.readData("Create Account", 1, 0);
		String email=exObj.readData("Create Account", 1, 1);
		
		
		//Enter name and email
		loginPageObj.enterSignupName(name);
		loginPageObj.enterSignupEmail(email);

		//Click 'Signup' button
		loginPageObj.clickSignupButton();
		
		String genderData=exObj.readData("Create Account", 1, 2);
		WebElement gender = signupPageObj.selectGender(genderData);
		jsClick(gender);
		
		String password=exObj.readData("Create Account", 1, 3);
		signupPageObj.enterPassword(password);
		
		String day=exObj.readData("Create Account", 1, 4);
		String month=exObj.readData("Create Account", 1, 5);
		String year=exObj.readData("Create Account", 1, 6);
		signupPageObj.selectDateOfBirth(day, month, year);

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
		
		signupPageObj.clickContinue();
		
		homePageObj.clickLogoutLink();

	}
	

}














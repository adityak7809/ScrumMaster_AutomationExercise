package GenericRepository;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import PropertyUtility.ReadPropertyFile;


public class BaseConfig {

	//Global Declaration
	public WebDriver driver;
	public ReadPropertyFile propFileObj;
	public static WebDriver static_driver;
	
	@Parameters({"browser", "username"})
	@BeforeClass
	public void browserSetup(String browser, String username) throws IOException
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
//		driver.close();
		Reporter.log("Browser closed: "+browser, true);
		
		Reporter.log("Test execution completed by: "+username, true);

	}



}

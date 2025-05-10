package GenericRepo;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import DataReadUtility.ReadPropertyFile;

public class BaseConfig {
	
	public WebDriver driver;
	public WebDriverWait wait;
	public ReadPropertyFile propObj;
	
	@Parameters({"browser"})
	@BeforeClass
	public void browserSetup(@Optional("edge") String browser) throws IOException
	{
		
		
		if(browser.equals("chrome"))
		{
			driver=new ChromeDriver();
			Reporter.log("Browser verified",true);
			Reporter.log("Opening "+browser,true);
		}
		if(browser.equals("edge"))
		{
			driver=new EdgeDriver();
			Reporter.log("Browser verified",true);
			Reporter.log("Opening "+browser,true);
		}
		if(browser.equals("firefox"))
		{
			driver=new FirefoxDriver();
			Reporter.log("Browser verified",true);
			Reporter.log("Opening "+browser,true);
		}
		
		propObj=new ReadPropertyFile();
		String url=propObj.readData("url");
		driver.get(url);
		Reporter.log("Navigating to "+url,true);
		
	}
	
	@Parameters({"browser"})
	@AfterClass
	public void terminateBrowser(@Optional("edge") String browser)
	{
//		driver.close();
		Reporter.log(browser+" closed",true);
	}

}

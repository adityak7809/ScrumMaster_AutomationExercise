package ListnersUtility;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import GenericRepository.BaseConfig;

public class Listners_Imp implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
    	
    	String name=result.getName();
    	
    	//1. Perform typecasting from webdriver to takescreenshot
    	TakesScreenshot ts = (TakesScreenshot) BaseConfig.static_driver;
    	
    	//2. Call the screenshot method
    	//3. Store the screenshot into temp path
    	File src=ts.getScreenshotAs(OutputType.FILE);
    	
    	//4. Create a new Permanent path for the screenshot

    	File dest=new File(System.getProperty("user.dir") + "/Screenshot/" + name + "_" + System.currentTimeMillis() + ".png");
		
		//5. Copy the Screenshot from Temp to Permanent Path
		try {
			FileHandler.copy(src, dest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Reporter.log("Screenshot taken_"+name,true);
    }

   

    
}

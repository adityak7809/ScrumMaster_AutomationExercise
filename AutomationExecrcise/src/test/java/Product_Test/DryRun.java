package Product_Test;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import PropertyUtility.ReadPropertyFile;

public class DryRun {
	WebDriver driver;
	@Test
	public void m1() throws IOException
	{
		ReadPropertyFile propObj=new ReadPropertyFile();
		driver=new ChromeDriver();
		String url=propObj.readData("url");
		driver.get(url);
		System.out.println(driver.getTitle());
		System.out.println(driver.findElement(By.xpath("//title[text()='Automation Exercise']")).getText());
	}

}

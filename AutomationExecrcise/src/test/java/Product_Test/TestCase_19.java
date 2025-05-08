package Product_Test;

import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.ProductsPage;
import PageRepository.ViewCartPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_19 extends BaseConfig {

	@Test
	public void View_and_Cart_Brand_Products() {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		ViewCartPage viewCartPageObj=new ViewCartPage(driver);

		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Click on 'Products' button
		homePageObj.clickProductsLink();

		// 4. Verify that Brands are visible on left side bar
		boolean brandsSection = productsPageObj.isBrandHeaderDisplayed();
		if(brandsSection==true) {
			Reporter.log("Brands section is visible on left side bar",true);
		} else {
			Reporter.log("Brands section is not visible",true);
		}

		// 5. Click on any brand name (first brand)
		WebElement brandPolo=productsPageObj.clickBrandPolo();
		jsClick(brandPolo); 

		// 6. Verify that user is navigated to brand page and brand products are displayed	
		boolean brandtitle = productsPageObj.isBrandTitledisplayed();
		if(brandtitle==true) 
		{
			Reporter.log("Successfully navigated to " + productsPageObj.getBrandTitleName() + " brand page",true);
			Reporter.log("Brand products are displayed successfully	",true);
		}
		else 
		{
			System.out.println("Failed to navigate to brand page");
			Reporter.log("Brand products are not displayed",true);
		}

		// 7. On left side bar, click on any other brand link (second brand)
		WebElement brandH_and_M=productsPageObj.clickBrandH_and_M();
		jsClick(brandH_and_M); 

		// 8. Verify that user is navigated to that brand page and can see products
		brandtitle = productsPageObj.isBrandTitledisplayed();
		if(brandtitle==true) 
		{
			Reporter.log("Successfully navigated to " + productsPageObj.getBrandTitleName() + " brand page",true);
			Reporter.log("Brand products are displayed successfully	",true);
		}
		else 
		{
			System.out.println("Failed to navigate to brand page");
			Reporter.log("Brand products are not displayed",true);
		}



	}
}
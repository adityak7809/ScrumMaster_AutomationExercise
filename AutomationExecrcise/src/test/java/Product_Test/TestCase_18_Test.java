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
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_18_Test extends BaseConfig {

	@Test
	public void ViewCategory_Products() throws InterruptedException {
		
		TestCase_18_Test classObj=new TestCase_18_Test();
		Reporter.log("Executing class:- "+"#"+printClassName(classObj)+"#",true);
		

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);

		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Verify that categories are visible on left side bar
		boolean categoriesSection = homePageObj.isCategoryHeaderDisplayed();
		if(categoriesSection==true) {
			Reporter.log("Categories section is visible on left side bar",true);
		} 
		else {
			Reporter.log("Categories section is not visible",true);
		}

		// 4. Click on 'Women' category
		WebElement womenCategory = homePageObj.clickWomenCatogory();
		jsClick(womenCategory);
		Reporter.log("Clicked on Women category",true);

		// 5. Click on any category link under 'Women' category, for example: Dress
		Thread.sleep(1000);

		WebElement dressSubcategory = homePageObj.clickWomenDressSubcategory();
		jsClick(dressSubcategory);
		Reporter.log("Clicked on Dress subcategory",true);

		// 6. Verify that category page is displayed and confirm text 'WOMEN - DRESS PRODUCTS'
		boolean categoryTitle = homePageObj.isCategoryTitleDisplayed();
		if(categoryTitle==true) {
			Reporter.log("Category page is displayed with correct title: " + homePageObj.getCategoryName(),true);
		} 
		else {
			Reporter.log("Category page verification failed",true);
		}

		// 7. On left side bar, click on any sub-category link of 'Men' category
		// First click to expand Men category
		WebElement menCategory = homePageObj.clickMenCatogory();
		jsClick(menCategory);

		// Then click on a subcategory (e.g., TShirt)
		Thread.sleep(1000);

		WebElement jeansSubcategory = homePageObj.clickMenTshirtsSubcategory();
		jsClick(jeansSubcategory);
		Reporter.log("Clicked on TShirt subcategory under Men",true);

		// 8. Verify that user is navigated to that category page
		boolean menCategoryTitle = homePageObj.isCategoryTitleDisplayed();
		if(menCategoryTitle==true) {
			Reporter.log("Successfully navigated to Men's TShirt category page: " + homePageObj.getCategoryName(),true);
		} 
		else {
			Reporter.log("Navigation to Men's category page failed",true);
		}


	}
}
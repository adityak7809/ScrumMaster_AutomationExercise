package Product_Test;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.ProductsPage;
import PropertyUtility.ReadPropertyFile;


@Listeners(Listners_Imp.class)
public class TestCase_09 extends BaseConfig {



	@Test
	public void Search_Product() throws IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);

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


		// 4. Click on 'Products' button
		homePageObj.clickProductsLink();

		// 5. Verify user is navigated to ALL PRODUCTS page successfully
		boolean allProductsHeader = productsPageObj.isAllproductHeaderDisplayed();
		if (allProductsHeader==true) {
			Reporter.log("User is navigated to ALL PRODUCTS page successfully",true);
		} else {
			Reporter.log("User is not navigated to ALL PRODUCTS page successfully",true);
		}

		// 6. Enter product name in search input and click search button
		String searchProductName = exObj.readData("Search Product", 2, 0);
		productsPageObj.searchProduct(searchProductName);

		// 7. Verify 'SEARCHED PRODUCTS' is visible
		boolean serachedProducts = productsPageObj.isSeachProductTitleDisplayed();
		if (serachedProducts==true) {
			Reporter.log("'SEARCHED PRODUCTS' is visible",true);
		} else {
			Reporter.log("'SEARCHED PRODUCTS' is not visible",true);
		}

		// 8. Verify all the products related to search are visible
		List<WebElement> ProductsName = productsPageObj.searchedproductName();
		for(WebElement displayedProductsName:ProductsName)
		{
			String prodName=displayedProductsName.getText();
			String regex = "[" + searchProductName + "]";
			if(prodName.matches(".*" + regex + ".*"))
			{
				Reporter.log("Product related to search is visible: "+prodName,true);
			}
			else 
			{
				Reporter.log("Product related to search is not visible: "+prodName,true);
			}
		}

	}
}
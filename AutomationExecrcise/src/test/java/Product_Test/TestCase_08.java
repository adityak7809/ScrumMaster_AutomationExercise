package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.ProductDetailsPage;
import PageRepository.ProductsPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_08 extends BaseConfig {



	@Test
	public void Verify_All_Products_and_product_detail_page() throws IOException {

		// Create Object Ref. variable
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductDetailsPage productDetailsPageObj=new ProductDetailsPage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);


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

		// 4. Click on 'Products' button
		homePageObj.clickProductsLink();

		// 5. Verify user is navigated to ALL PRODUCTS page successfully
		boolean allProductsHeader = productsPageObj.isAllproductHeaderDisplayed();
		if (allProductsHeader==true) {
			Reporter.log("User is navigated to ALL PRODUCTS page successfully",true);
		} else {
			Reporter.log("User is not navigated to ALL PRODUCTS page successfully",true);
		}

		// 6. The products list is visible
		boolean productsList = productsPageObj.isAllproductListDisplayed();
		if (productsList==true) {
			Reporter.log("Product list is visible",true);
		} else {
			Reporter.log("Product list is not visible",true);
		}

		// 7. Click on 'View Product' of first product
		jsClick(productsPageObj.viewProductDetails(0));

		// 8. User is landed to product detail page
		boolean productDetailsTitle = productDetailsPageObj.isProductDetailsPageDisplayed(driver);
		if (productDetailsTitle==true) {
			Reporter.log("User is landed to product details page",true);
		} else {
			Reporter.log("User is not landed to product details page",true);
		}

		// 9. Verify that product detail is visible: product name, category, price, availability, condition, brand
		String productName = productDetailsPageObj.getProductName();
		String productCategory = productDetailsPageObj.getProductCategory();
		String productPrice = productDetailsPageObj.getProductPrice();
		String productAvailability = productDetailsPageObj.getProductAvailability();
		String productCondition = productDetailsPageObj.getProductCondition();
		String productBrand = productDetailsPageObj.getProductBrand();

		if(!productName.isEmpty() && !productCategory.isEmpty() &&!productPrice.isEmpty() &&!productAvailability.isEmpty() &&!productCondition.isEmpty() &&!productBrand.isEmpty() )
		{
			Reporter.log("Product details are visible:\n"
					+ "Product Name: "+productName+"\n"
					+ "Product Category: "+productCategory+"\n"
					+ "Product Price: "+productPrice+"\n"
					+ "Product Availability: "+productAvailability+"\n"
					+ "Product Condition: "+productCondition+"\n"
					+ "Product Brand: "+productBrand+"\n",true);
		}
		else
		{
			Reporter.log("Product details are not visible",true);
		}
	}
}
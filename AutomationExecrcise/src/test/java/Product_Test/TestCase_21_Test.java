package Product_Test;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import ExcelUtility.ReadExcelFile;
import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;
import PageRepository.HomePage;
import PageRepository.ProductDetailsPage;
import PageRepository.ProductsPage;
import PropertyUtility.ReadPropertyFile;

@Listeners(Listners_Imp.class)
public class TestCase_21_Test extends BaseConfig {

	@Test
	public void Add_review_on_product() throws EncryptedDocumentException, IOException {

		// Create Object Ref. variable
		ReadExcelFile exObj=new ReadExcelFile();
		ReadPropertyFile propObj=new ReadPropertyFile();

		//POM Class
		HomePage homePageObj=new HomePage(driver);
		ProductsPage productsPageObj=new ProductsPage(driver);
		ProductDetailsPage productDetailsPageObj=new ProductDetailsPage(driver);


		// 1. Launch browser- Script in BaseConfig
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		// 3. Click on 'Products' button
		homePageObj.clickProductsLink();

		// 4. Verify user is navigated to ALL PRODUCTS page successfully
		boolean allProductsHeader = productsPageObj.isAllproductHeaderDisplayed();
		if (allProductsHeader==true) {
			Reporter.log("User is navigated to ALL PRODUCTS page successfully",true);
		} else {
			Reporter.log("User is not navigated to ALL PRODUCTS page successfully",true);
		}

		// 5. Click on 'View Product' button (first product)
		WebElement viewProduct= productsPageObj.clickFirstViewProduct();
		jsClick(viewProduct);

		// 6. Verify 'Write Your Review' is visible
		boolean reviewSection = productDetailsPageObj.writeYourReviewDisplayed();
		if(reviewSection==true) {
			Reporter.log("'Write Your Review' section is visible",true);
		}
		else 
		{
			Reporter.log("Review section not available for this product",true);
		}

		// Scroll to the review section (simple JavaScript scroll)
		jsScrollIntoView(productDetailsPageObj.writeYourReviewheaderText());

		// 7. Enter name, email and review
		String name=exObj.readData("Product Detail", 1, 0);
		String email=exObj.readData("Product Detail", 1, 1);
		String reviewMsg=exObj.readData("Product Detail", 1, 2);

		productDetailsPageObj.submitReview(name, email, reviewMsg);
		Reporter.log("Entered review details",true);

		// 8. Click 'Submit' button
		productDetailsPageObj.clickSubmit();

		// 9. Verify success message 'Thank you for your review.'
		boolean successMessage = productDetailsPageObj.isReviewSubmittedSuccessfully();
		if(successMessage==true) 
		{
			Reporter.log("'Thank you for your review.' is visible ",true);
		} 
		else 
		{
			Reporter.log("Success message not found",true);
		}




	}
}
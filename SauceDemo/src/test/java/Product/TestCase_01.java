package Product;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import DataReadUtility.ReadExcelFile;
import GenericRepo.BaseConfig;
import PageRepo.AllProductPage;
import PageRepo.CheckoutCompletePage;
import PageRepo.CheckoutOverviewPage;
import PageRepo.CheckoutPage;
import PageRepo.LoginPage;
import PageRepo.YourCartPage;

public class TestCase_01 extends BaseConfig {
	
	
	@Test
	public void login() throws IOException {
		
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		
		
		LoginPage loginObj=new LoginPage(driver);
		AllProductPage allProductPageobj=new AllProductPage(driver);
		YourCartPage yourCartPageObj=new YourCartPage(driver);
		CheckoutPage checkoutPageObj=new CheckoutPage(driver);
		CheckoutOverviewPage checkoutOverviewPageObj=new CheckoutOverviewPage(driver);
		CheckoutCompletePage checkoutCompletePageObj=new CheckoutCompletePage(driver);
		
		ReadExcelFile excelObj=new ReadExcelFile();

		
		//
		
		String loginPageTitle=loginObj.getPageTitle().getText();
		if(loginPageTitle.equals("Swag Labs"))
		{
			Reporter.log("Home page successfully loaded",true);
		}
		else
		{
			Reporter.log("Home page not loaded",true);
		}
		
		String username=propObj.readData("username");
		String password=propObj.readData("password");
		loginObj.getUsername().sendKeys(username);
		loginObj.getPassword().sendKeys(password);
		
		loginObj.getLoginButton().click();
		
		//
		
		String productPageTitle=allProductPageobj.getPageTitle().getText();
		if(productPageTitle.equals("Products"))
		{
			Reporter.log("All product page successfully loaded",true);
		}
		else
		{
			Reporter.log("All product page not loaded",true);
		}
		
		WebElement addFirstProduct=allProductPageobj.getAddToCart1Button();
		addFirstProduct.click();
		WebElement addSecondProduct=allProductPageobj.getAddToCart2Button();
		addSecondProduct.click();
		
		allProductPageobj.getCartButton().click();
		
		//
	
		String yourCartPageTitle=yourCartPageObj.getPageTitle().getText();
		if(yourCartPageTitle.equals("Your Cart"))
		{
			Reporter.log("Your cart page successfully loaded",true);
		}
		else
		{
			Reporter.log("Your cart page not loaded",true);
		}
		
		int totalAddedProduct=2;
		for(int index =0; index<totalAddedProduct; index++)
		{
			String prodName=yourCartPageObj.getProductName(index).getText();
			String prodQuantity=yourCartPageObj.getQuantity(index).getText();
			Reporter.log("Product Name: "+prodName,true);
			Reporter.log("Product Quantity: "+prodQuantity,true);
		}
		
		yourCartPageObj.getCheckoutButton().click();
		
		//
		
		String checkoutInfoPageTitle=checkoutPageObj.getPageTitle().getText();
		if(checkoutInfoPageTitle.equals("Checkout: Your Information"))
		{
			Reporter.log("Checkout info page successfully loaded",true);
		}
		else
		{
			Reporter.log("Checkout info page not loaded",true);
		}
		
		String firstname=excelObj.readData("UserDetails", 1, 0);
		String lastname=excelObj.readData("UserDetails", 1, 1);
		String zip=excelObj.readData("UserDetails", 1, 2);
		
		checkoutPageObj.getFirstName().sendKeys(firstname);
		checkoutPageObj.getLastName().sendKeys(lastname);
		checkoutPageObj.getZip().sendKeys(zip);
		
		checkoutPageObj.getContinueButton().click();
		
		//
		
		String checkoutOverviewPageTitle=checkoutOverviewPageObj.getPageTitle().getText();
		if(checkoutOverviewPageTitle.equals("Checkout: Overview"))
		{
			Reporter.log("Checkout overview page successfully loaded",true);
		}
		else
		{
			Reporter.log("Checkout overview page not loaded",true);
		}
		
		String payInfo=checkoutOverviewPageObj.getPayementInfo().getText();
		String shipInfo=checkoutOverviewPageObj.getShippingInfo().getText();
		String total=checkoutOverviewPageObj.getTotalPrice().getText();
		
		Reporter.log("Payment Information: "+payInfo,true);
		Reporter.log("Shipping Information: "+shipInfo,true);
		Reporter.log("Price Total: "+total,true);
		
		checkoutOverviewPageObj.getFinishButton().click();
		
		//
		
		String checkoutCompletePageTitle=checkoutCompletePageObj.getPageTitle().getText();
		if(checkoutCompletePageTitle.equals("Checkout: Complete!"))
		{
			Reporter.log("Checkout complete page successfully loaded",true);
		}
		else
		{
			Reporter.log("Checkout complete page not loaded",true);
		}
		
		String successmsg=checkoutCompletePageObj.getSuccessMsg().getText();
		if(successmsg.equals("Thank you for your order!"))
		{
			Reporter.log("Success msg displayed: "+successmsg,true);
		}
		else
		{
			Reporter.log("Success msg not displayed",true);
		}
		
		checkoutCompletePageObj.getBackHomeButton().click();
		
		//
		
		allProductPageobj.getMenuButtons().click();
		
		wait.until(ExpectedConditions.elementToBeClickable(allProductPageobj.getLogoutButton())).click();
		
		
		
		
	}

}

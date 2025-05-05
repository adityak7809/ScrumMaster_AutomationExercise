package Product_Test;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import GenericRepository.BaseConfig;
import ListnersUtility.Listners_Imp;

@Listeners(Listners_Imp.class)
public class TestCase_14 extends BaseConfig {

	@Test
	public void Place_Order_Register_while_Checkout() throws InterruptedException {

		//Generate random email
		Random random=new Random();
		int randomNum1=random.nextInt(1000);
		int randomNum2=random.nextInt(1000);

		String email="abc"+randomNum1+"xyz"+randomNum2+"@gmail.com";

		// 1. Launch browser- Script in BaseConfig

		// Javascript Code
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// 2. Navigate to url 'http://automationexercise.com'- Script in BaseConfig

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); 

		//		3. Verify that home page is visible successfully
		String actPageTitle=driver.getTitle();
		String expPageTille="Automation Exercise";
		if(actPageTitle.equals(expPageTille))
		{
			System.out.println("Home page is visible successfully");
		}
		else
		{
			System.out.println("Home page is not displayed");
		}


		//		4. Add products to cart
		// Add first product
		WebElement addToCart1= driver.findElement(By.xpath("(//a[@data-product-id='1'])[1]"));
		js.executeScript("arguments[0].click();", addToCart1);

		// Click Continue Shopping
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();

		// Add second product
		WebElement addToCart2= driver.findElement(By.xpath("(//a[@data-product-id='2'])[1]"));
		js.executeScript("arguments[0].click();", addToCart2);

		// Click Continue Shopping
		Thread.sleep(1000);
		driver.findElement(By.xpath("//button[text()='Continue Shopping']")).click();


		//		5. Click 'Cart' button
		WebElement cart= driver.findElement(By.xpath("//a[@href='/view_cart']"));
		cart.click();

		//		6. Verify that cart page is displayed
		String cartTitle="Automation Exercise - Checkout";
		String actCartTitlte=driver.getTitle();

		if(cartTitle.equals(actCartTitlte))
		{
			System.out.println("Cart page is displayed successfully");
		}
		else
		{
			System.out.println("Cart page is not displayed");
		}

		//		7. Click Proceed To Checkout
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

		//		8. Click 'Register / Login' button
		driver.findElement(By.xpath("//u[text()='Register / Login']")).click();

		//		9. Fill all details in Signup and create account
		driver.findElement(By.xpath("//div[@class='signup-form']/form/input[2]")).sendKeys("Ogggy");
		driver.findElement(By.xpath("//input[@data-qa='signup-email']")).sendKeys(email);

		driver.findElement(By.xpath("//button[text()='Signup']")).click();

		WebElement gender=driver.findElement(By.id("id_gender1"));
		js.executeScript("arguments[0].click();", gender);

		WebElement password=driver.findElement(By.name("password"));
		js.executeScript("arguments[0].value='abc123'", password);

		WebElement day=driver.findElement(By.id("days"));
		Select day_drop=new Select(day);
		day_drop.selectByVisibleText("21");

		WebElement month=driver.findElement(By.id("months"));
		Select month_drop=new Select(month);
		month_drop.selectByVisibleText("December");

		WebElement year=driver.findElement(By.id("years"));
		Select year_drop=new Select(year);
		year_drop.selectByVisibleText("2000");
		Thread.sleep(2000);
		WebElement checkbox1 = driver.findElement(By.xpath("//div[@class='checkbox']/div/span/input[@id='newsletter']"));
		js.executeScript("arguments[0].click();", checkbox1);

		WebElement checkbox2 = driver.findElement(By.xpath("//div[@class='checkbox']/div/span/input[@id='optin']"));
		js.executeScript("arguments[0].click();", checkbox2);

		driver.findElement(By.name("first_name")).sendKeys("Ogggy");
		driver.findElement(By.name("last_name")).sendKeys("Sharma");
		driver.findElement(By.name("company")).sendKeys("Q3Technologies");
		driver.findElement(By.name("address1")).sendKeys("Kondapur");
		driver.findElement(By.name("address2")).sendKeys("Raghvendra Colony");

		WebElement country= driver.findElement(By.name("country"));
		Select country_drop=new Select(country);
		country_drop.selectByVisibleText("India");

		js.executeScript("window.scrollBy(0, 400)");
		driver.findElement(By.name("state")).sendKeys("Talengana");
		driver.findElement(By.name("city")).sendKeys("Hyderabad");
		driver.findElement(By.name("zipcode")).sendKeys("500084");
		driver.findElement(By.name("mobile_number")).sendKeys("9341205186");

		js.executeScript("window.scrollBy(0, 400)");
		driver.findElement(By.xpath("//form[@action='/signup']/button[@type='submit']")).click();

		//		10. Verify 'ACCOUNT CREATED!' and click 'Continue' button
		WebElement text3=driver.findElement(By.xpath("//h2/b"));
		if(text3.isDisplayed())
		{
			System.out.println("'ACCOUNT CREATED!' is visible");
			driver.findElement(By.xpath("//div[@class='pull-right']/a")).click();
		}
		else
		{
			System.out.println("'ACCOUNT CREATED!' is not visible");
		}

		//		11. Verify ' Logged in as username' at top
		WebElement text4=driver.findElement(By.xpath("//a[contains(text(),'Logged in as')]"));
		if(text4.isDisplayed())
		{
			System.out.println("'Logged in as username' is visible");
		}
		else
		{
			System.out.println("'Logged in as username' is not visible");
		}

		//		12.Click 'Cart' button
		driver.findElement(By.xpath("(//a[@href=\"/view_cart\"])[1]")).click();

		//		13. Click 'Proceed To Checkout' button
		driver.findElement(By.xpath("//a[text()='Proceed To Checkout']")).click();

		//		14. Verify Address Details and Review Your Order
		List<WebElement> deliveryAddress=driver.findElements(By.xpath("//ul[@id='address_delivery']/li"));
		List<WebElement> billingAddress=driver.findElements(By.xpath("//ul[@id='address_delivery']/li"));

		if(deliveryAddress.containsAll(billingAddress))
		{
			System.out.println("Address Details are successfully verified");
		}
		else
		{
			System.out.println("Address Details are not verified");
		}

		WebElement prodName=driver.findElement(By.xpath("//a[text()='Blue Top']"));
		WebElement price=driver.findElement(By.xpath("(//p[text()='Rs. 500'])[1]"));
		WebElement quantity=driver.findElement(By.xpath("//button[text()='1']"));

		if(prodName.isDisplayed())
		{
			if(price.isDisplayed())
			{
				if(quantity.isDisplayed())
				{
					System.out.println("Review order verified successfully");
				}
				else
				{
					System.out.println("Review order not verified");
				}
			}
		}

		//		15. Enter description in comment text area and click 'Place Order'
		driver.findElement(By.name("message")).sendKeys("All details verified successfully");
		driver.findElement(By.xpath("//a[text()='Place Order']")).click();

		//		16. Enter payment details: Name on Card, Card Number, CVC, Expiration date
		driver.findElement(By.name("name_on_card")).sendKeys("Oggy Sharma");
		driver.findElement(By.name("card_number")).sendKeys("1234567891011");
		driver.findElement(By.name("cvc")).sendKeys("123");
		driver.findElement(By.name("expiry_month")).sendKeys("10");
		driver.findElement(By.name("expiry_year")).sendKeys("2026");

		//		17. Click 'Pay and Confirm Order' button
		WebElement confirmOrder = driver.findElement(By.id("submit"));
		js.executeScript("arguments[0].click();", confirmOrder);

		//		18. Verify success message 'Your order has been placed successfully!'
		if(driver.findElement(By.xpath("(//div[@class='alert-success alert'])[1]")).isDisplayed())
		{
			System.out.println("'Your order has been placed successfully!' verified successfully");
		}
		else
		{
			System.out.println("'Your order has been placed successfully!' not verified");
		}

		//		19. Click 'Delete Account' button
		driver.findElement(By.xpath("//a[@href='/delete_account']")).click();


		//		20. Verify 'ACCOUNT DELETED!' and click 'Continue' button
		WebElement text5= driver.findElement(By.xpath("//h2/b[text()='Account Deleted!']"));
		if(text5.isDisplayed())
		{
			System.out.println("'ACCOUNT DELETED!' is visible");
			driver.findElement(By.xpath("//a[text()='Continue']")).click();
		}
		else
		{
			System.out.println("'ACCOUNT DELETED!' is not visible");
		}



	}

}












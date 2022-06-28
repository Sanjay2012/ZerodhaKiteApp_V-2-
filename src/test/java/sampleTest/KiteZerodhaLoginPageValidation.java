package sampleTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KiteZerodhaLoginPageValidation {

	@Test
	public void zerodhaLoginPageValidation() {

		SoftAssert soft = new SoftAssert();

		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		WebDriver driver = new ChromeDriver(options);

		driver.get("https://kite.zerodha.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// implicitly wait is applicable for all elements present in page

		driver.manage().window().maximize();

		/**
		 * Kite zerodha login page 1 validation --> validate page title, page text -->
		 * Validation of element status --> validate error message for invalid
		 * credentials
		 */

		String pageTitle = driver.getTitle(); // actual

		// page title validation
		soft.assertEquals(pageTitle, " Zerodha's fast and elegant flagship trading platform");

//		 soft.assertTrue(driver.getPageSource().contains("Kite - Zerodha's fast and elegant flagship trading platform"));

		// Page text validation

//		String page1Text = driver.findElement(By.xpath("//div[@class=\"form-header text-center\"]")).getText();
//		soft.assertEquals(page1Text, "Login to Kite");

		soft.assertTrue(driver.getPageSource().contains("Login to Kite"));

		// validation of text field

		driver.findElement(By.xpath("//*[text()='Login ']")).click();

		soft.assertTrue(driver.getPageSource().contains("User ID should be minimum 6 characters."));

		soft.assertTrue(driver.getPageSource().contains("Password should be minimum 6 characters."));

		// -------------Text box, button and link validation--------------

		soft.assertTrue(driver.findElement(By.id("userid")).isEnabled());

		soft.assertTrue(driver.findElement(By.id("password")).isEnabled());

		soft.assertTrue(driver.findElement(By.xpath("//*[text()='Login ']")).isEnabled());

		soft.assertTrue(driver.findElement(By.xpath("//a[@class=\"text-light forgot-link\"]")).isEnabled());

		// -------- Login with invalid credentials--> validate error message----------

		driver.findElement(By.id("userid")).sendKeys("KW0610"); // valid UN

		driver.findElement(By.id("password")).sendKeys("Test@123"); // invalid pwd

		driver.findElement(By.xpath("//*[text()='Login ']")).click(); // click on login button

		soft.assertTrue(driver.findElement(By.xpath("//*[@class=\"error text-center\"]")).isDisplayed());

		soft.assertAll();

		driver.quit();

	}

}
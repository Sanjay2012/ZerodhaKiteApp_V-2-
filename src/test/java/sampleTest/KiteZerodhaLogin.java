package sampleTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class KiteZerodhaLogin {

	@Test
	public void zerodhaLoginValidation(){
		SoftAssert soft=new SoftAssert();
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();

		driver.get("https://kite.zerodha.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		
		/**
		 * Kite zerodha login-logout functionality ----------> Profile Name Validation
		 *
		 */
		// -------- Login with valid credentials-----------------
		
		// page 1

		driver.findElement(By.id("userid")).clear();

		driver.findElement(By.id("userid")).sendKeys("KW0610"); // enter valid UN

		driver.findElement(By.id("password")).clear();

		driver.findElement(By.id("password")).sendKeys("Shiv@123"); // enter valid pass

		driver.findElement(By.xpath("//*[text()='Login ']")).click(); // click on login button
		
		// page 2
		
		driver.findElement(By.xpath("//*[text()='Continue ']")).click();
		
		soft.assertTrue(driver.getPageSource().contains("This field is required"));

		driver.findElement(By.id("pin")).sendKeys("152022");// enter pin

		soft.assertTrue(driver.findElement(By.xpath("//*[text()='Continue ']")).isEnabled());

		driver.findElement(By.xpath("//*[text()='Continue ']")).click(); // click on continue
		
		
		// page 3
		
		//soft.softEquals(driver.getTitle(), "Dashboard / Kite");
		//soft.softTrue(driver.getPageSource().contains("Dashboard"));

		String profileText = driver.findElement(By.xpath("//*[@class=\"user-id\"]")).getText();

		soft.assertEquals(profileText, "KW0610");
		
		String profileName=driver.findElement(By.xpath("//*[@class=\"avatar\"]")).getText();
		soft.assertEquals(profileName, "SB");

		// logout

		soft.assertTrue(driver.findElement(By.className("user-id")).isEnabled());
		driver.findElement(By.className("user-id")).click();
		
		soft.assertTrue(driver.findElement(By.xpath("//a[text()=' Logout']")).isEnabled());
		driver.findElement(By.xpath("//a[text()=' Logout']")).click();
		
		soft.assertTrue(driver.getPageSource().contains("Login"));
		
		soft.assertTrue(driver.getPageSource().contains("Change user"));

		driver.findElement(By.xpath("//*[text()='Change user']")).click();

		// page title validation
		
		soft.assertEquals(driver.getTitle(), "Kite - Zerodha's fast and elegant flagship trading platform");

		// Page text validation

		soft.assertTrue(driver.getPageSource().contains("Login to Kite"));

		soft.assertAll();
		driver.quit();

	}

}

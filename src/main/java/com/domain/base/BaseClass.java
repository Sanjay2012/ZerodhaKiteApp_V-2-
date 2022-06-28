package com.domain.base;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public Properties prop;

	@Parameters("browser")
	@BeforeClass
	public void setupApplication(String browser) {
		//readConfig();
		if (browser.equalsIgnoreCase("chrome")) {
			
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "//drivers/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--incognito");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(capabilities);
			driver = new ChromeDriver(options);
		}

		else if (browser.equalsIgnoreCase("firefox")) {
		//	System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "//drivers/geckodriver.exe");
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			options.addArguments("-incognito");
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setCapability(FirefoxOptions.FIREFOX_OPTIONS, options);
			driver = new FirefoxDriver(options);
		}
	}

	@AfterClass
	public void closeApplication() {
		driver.quit();
	}

	@AfterMethod  // after execution of test method @Test
	public void takeScreenShotOnFailure(ITestResult testResult) throws IOException {
		if (testResult.getStatus() == ITestResult.FAILURE) {
			captureScreenshot(driver, testResult.getName());
		}

	}
	
	
	/**
	 * This method is for capture the screenshots once any method get failed
	 * 
	 * @param driver
	 * @param testName -- name of failed method
	 */

	public static void captureScreenshot(WebDriver driver, String testName) throws IOException {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Screenshots/" + testName + ".png");
			FileHandler.copy(scrFile, destFile);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

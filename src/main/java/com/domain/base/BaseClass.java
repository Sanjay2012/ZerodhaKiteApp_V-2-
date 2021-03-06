package com.domain.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.domain.utilityClass.Utility;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public WebDriver driver;
	public Properties prop;

	@Parameters("browser")
	@BeforeClass
	public void setupApplication(String browser) {
		readConfig();
		if (browser.equalsIgnoreCase("chrome")) {
			
//			System.setProperty("webdriver.chrome.driver",
//					System.getProperty("user.dir") + "//drivers/chromedriver.exe");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			//options.addArguments("--headless");
			options.addArguments("--incognito");
			options.addArguments("--disabled-notifications");
		
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
			Utility.captureScreenshot(driver, testResult.getName());
		}

	}
	
	/*
	 * Method to read config properties file
	 */
	public void readConfig() {
		try {
			prop = new Properties();
			FileInputStream file = new FileInputStream("./src/test/resources/config/Configuration.properties");
			prop.load(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}

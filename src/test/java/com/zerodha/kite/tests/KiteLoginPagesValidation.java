package com.zerodha.kite.tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.base.BaseClass;
import com.zerodha.kite.pages.KiteLoginPage1;

public class KiteLoginPagesValidation extends BaseClass {
	public KiteLoginPage1 login1;
	SoftAssert soft = new SoftAssert();

	@Test(description = "Test Page title and Page Text", priority = 1)
	public void verifyKiteLoginPageTitle() {

		// open url in defined driver
		driver.get("https://kite.zerodha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("=====Application Started=====", true);
		// calling methods from POM class -- KiteLoginPage1
		login1 = new KiteLoginPage1(driver);
		
		// webelemnt status validation
		soft.assertEquals(login1.getPageTitle(), "Kite - Zerodha's fast and elegant flagship trading platform");
		soft.assertTrue(driver.getPageSource().contains("Login to Kite"));
		soft.assertAll();
	}

	@Test(description = "Test element status", priority = 2)
	public void verifyElementStatus() {
		login1 = new KiteLoginPage1(driver);
		soft.assertTrue(login1.verifyUserIdField());
		soft.assertTrue(login1.verifyPassField());
		soft.assertTrue(login1.verifyLoginButton());
		soft.assertTrue(login1.verifyForgotLink());
		soft.assertAll();
	}

	@Test(description = "Test Field Error Messages", priority = 3)
	public void verifyFieldValidation() {
		login1 = new KiteLoginPage1(driver);
		// check error message
		login1.clickKiteLoginPage1LoginButton();

		soft.assertTrue(driver.getPageSource().contains("User ID should be minimum 6 characters."));
		soft.assertTrue(driver.getPageSource().contains("Password should be minimum 6 characters."));
		soft.assertAll();
	}

	@Test(description = "Invalid Credential Error Message", priority = 4)
	public void verifyInvalidCredentialError() {
		login1 = new KiteLoginPage1(driver);
		// check error message upon entering invalid credentials

		login1.setKiteLoginPage1Username("SW0712");

		login1.setKiteLoginPage1Password("Shiv123");

		login1.clickKiteLoginPage1LoginButton();

		soft.assertTrue(login1.validateErrorMessage());

		soft.assertAll();
	}

}

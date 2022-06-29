package com.zerodha.kite.tests;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.base.BaseClass;
import com.domain.utilityClass.Utility;
import com.zerodha.kite.pages.KiteLoginPage1;

public class KiteLoginPagesValidationWithDD extends BaseClass {
	public KiteLoginPage1 login1;
	SoftAssert soft = new SoftAssert();

	@Test(description = "Test Page title and Page Text", priority = 1)
	public void verifyKiteLoginPageTitle() throws EncryptedDocumentException, IOException {

		// open url in defined driver
		driver.get(prop.getProperty("kiteUrl"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		Reporter.log("=====Application Started=====", true);
		// calling methods from POM class -- KiteLoginPage1
		login1 = new KiteLoginPage1(driver);
		
		// webelemnt status validation
//		soft.assertEquals(login1.getPageTitle(), prop.getProperty("kiteLoginPageTitle"));
//		soft.assertTrue(driver.getPageSource().contains(prop.getProperty("kiteLoginPageText")));
//		
		soft.assertEquals(login1.getPageTitle(), Utility.getTestData(0, 0));
		soft.assertTrue(driver.getPageSource().contains(Utility.getTestData(1, 0)));
		
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

		soft.assertTrue(driver.getPageSource().contains(prop.getProperty("userIdError")));
		soft.assertTrue(driver.getPageSource().contains(prop.getProperty("passError")));
		soft.assertAll();
	}

	@Test(description = "Invalid Credential Error Message", priority = 4)
	public void verifyInvalidCredentialError() {
		login1 = new KiteLoginPage1(driver);
		// check error message upon entering invalid credentials

		login1.setKiteLoginPage1Username(prop.getProperty("kiteInvalidUserId"));

		login1.setKiteLoginPage1Password(prop.getProperty("kiteInalidPassword"));

		login1.clickKiteLoginPage1LoginButton();

		soft.assertTrue(login1.validateErrorMessage());

		soft.assertAll();
	}

}

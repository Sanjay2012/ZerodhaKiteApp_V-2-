package com.zerodha.kite.tests;

import java.time.Duration;

import org.testng.Reporter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.domain.base.BaseClass;
import com.zerodha.kite.pages.KiteHomePage;
import com.zerodha.kite.pages.KiteLoginPage1;
import com.zerodha.kite.pages.KiteLoginPage2;
import com.zerodha.kite.pages.KitePageAfterLogout;

public class KiteLoginTest extends BaseClass {
	public KiteLoginPage1 login1;
	public KiteLoginPage2 login2;
	public KiteHomePage home;
	public KitePageAfterLogout logout;
	SoftAssert soft=new SoftAssert();
	
	@Test(description = "Test Kite Login Page-1", priority = 1)
	public void verifyKiteLoginPage1() {
		login1 = new KiteLoginPage1(driver);
		
		driver.get("https://kite.zerodha.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		Reporter.log("=====Application Started=====", true);
		// calling methods from POM class -- KiteLoginPage1
		
		soft.assertEquals(login1.getPageTitle(), "Kite - Zerodha's fast and elegant flagship trading platform");
		soft.assertTrue(driver.getPageSource().contains("Login to Kite"));
		login1.setKiteLoginPage1Username("KW0610");
		login1.setKiteLoginPage1Password("Shiv@123");
		login1.clickKiteLoginPage1LoginButton();
		soft.assertAll();
	}
		
		@Test(description = "Test Kite Login Page-2", priority = 2, dependsOnMethods = "verifyKiteLoginPage1")
		public void verifyKiteLoginPage2() {	
		// calling methods from page2
		login2 = new KiteLoginPage2(driver);
		
		login2.setKiteLoginPage2Pin("152022");
		
		soft.assertTrue(login2.verifyContinueButton());
		
		login2.clickKiteLoginPage2ContinueButton();
		soft.assertAll();
	}
		
	@Test(description = "Test kite Home Page", priority = 3, dependsOnMethods = "verifyKiteLoginPage2")
	public void homePageValidation() {
		
		home = new KiteHomePage(driver);

		//String profileName = home.veryfyKiteHomePageProfileName();
		soft.assertEquals(home.veryfyKiteHomePageProfileName(), "SB");

		//String userId = home.veryfyKiteHomeUserId();
		soft.assertEquals(home.veryfyKiteHomeUserId(), "KW0610");
		soft.assertTrue(home.verifyMenuButtonStatus());
		home.clickOnProfileMenuButton();
		soft.assertAll();
	}
	
	@Test(description = "Test Logout functionality", priority = 4, dependsOnMethods = "verifyKiteLoginPage2")
	public void verifyLogoutFunctionality() {
		// validate logout button
		soft.assertTrue(home.verifyLogoutButton());
		home.clickOnLogoutButton();
		soft.assertAll();
		
	}
	
	@Test(description = "Test after logout Page", priority = 5, dependsOnMethods = "verifyKiteLoginPage2")
	public void verifyAfterLogoutPage() {
		
		logout = new KitePageAfterLogout(driver);
		
		soft.assertTrue(logout.validateLink());
		soft.assertTrue(driver.getPageSource().contains("KW0610"));
		
		logout.clickOnChangeUser();
		
		soft.assertAll();
	}
	
	@Test(description = "Test after logout Page", priority = 6,dependsOnMethods = "verifyAfterLogoutPage" )
	public void verifyLoginPageAfterLogout() {
		// verify user logout successfully
		login1 = new KiteLoginPage1(driver);
		soft.assertEquals(login1.getPageTitle(), "Kite - Zerodha's fast and elegant flagship trading platform");
		soft.assertTrue(driver.getPageSource().contains("Login to Kite"));
		soft.assertAll();
	}
			
}
		
		

package com.zerodha.kite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
public class KiteLoginPage1{
	
	public WebDriver driver;

		// declaration
		@FindBy(xpath = "//*[@id=\"userid\"]") 
		private WebElement UN;
		
		@FindBy(xpath = "//*[@id=\"password\"]")
		private WebElement PWD;
		
		@FindBy(xpath = "//*[@class=\"button-orange wide\"]")
		private WebElement loginButton;
		
		@FindBy(xpath = "//*[@class=\"error text-center\"]")
		private WebElement errorMessage;
		
		@FindBy(xpath="//*[@class=\"text-light forgot-link\"]")
		private WebElement forgotLink;
	
		
		public KiteLoginPage1(WebDriver driver) {
			this.driver = driver;
			PageFactory.initElements(driver, this); //Lazy initilization
			// POM--> early initilization
		}
		
		public void
		setKiteLoginPage1Username(String userName) {
			UN.clear();
			UN.sendKeys(userName);
		}
	
		
		public void
		setKiteLoginPage1Password(String password) {
			PWD.clear();
			PWD.sendKeys(password);
		}
		
		
		public void
		clickKiteLoginPage1LoginButton() {
			loginButton.click();
			
		}
		
		public boolean validateErrorMessage() {
			return errorMessage.isDisplayed();
		}
			
		
		public String getPageTitle() {
			return driver.getTitle();
			 
		}
		
		public boolean verifyUserIdField() {
			return UN.isEnabled();
		}
		
		
		public boolean verifyPassField() {
			return PWD.isEnabled();
		}
		
		public boolean verifyLoginButton() {
			return loginButton.isEnabled();	
		}
		
		public boolean verifyForgotLink() {
			return forgotLink.isEnabled();
			
		}
		

}

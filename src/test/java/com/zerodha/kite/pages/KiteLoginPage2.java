package com.zerodha.kite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KiteLoginPage2{
	
	public WebDriver driver;
	public KiteLoginPage2(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
		}

			// declaration
			@FindBy(xpath="//input[@id=\"pin\"]") 
			private WebElement PIN;
			
			@FindBy(xpath="//*[text()='Continue ']")
			private WebElement continueButton;
			
			@FindBy(xpath = "//*[@class=\"error text-center\"]")
			private WebElement errorMessage;
	
			
			
			public void
			setKiteLoginPage2Pin(String pinValue) {
				PIN.clear();
				PIN.sendKeys(pinValue);
			}
			
			public boolean verifyContinueButton() {
				return continueButton.isDisplayed();
				 
			}
			
			public void
			clickKiteLoginPage2ContinueButton() {
				continueButton.click();
			}

}

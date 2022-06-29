package com.zerodha.kite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KitePageAfterLogout  {
	public WebDriver driver;
	
	@FindBy(className = "avatar")
	private WebElement profileName;
	
	@FindBy(xpath = "//*[@id=\"password\"]")
	private WebElement PWD;
	
	@FindBy(xpath = "//*[text()='Change user']")
	private WebElement changeUserLink;

	

	public KitePageAfterLogout(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}


	public String verifyProfileNameAfterLogout() {
		String actual = profileName.getText();
		return actual;

	}
	
	public boolean validateLink() {
		return changeUserLink.isEnabled();
		
	}
	
	public void clickOnChangeUser() {
		changeUserLink.click();
	}

}

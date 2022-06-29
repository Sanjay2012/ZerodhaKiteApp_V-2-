package com.zerodha.kite.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class KiteHomePage {
	public WebDriver driver;
	// declaration
	@FindBy(xpath = "//*[@class=\"avatar\"]")
	private WebElement profileName;

	@FindBy(xpath = "//span[@class=\"user-id\"]")
	private WebElement menuButton;

	@FindBy(xpath = "//*[@class=\"icon icon-exit\"]")
	private WebElement logoutButton;

	public KiteHomePage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	public String veryfyKiteHomePageProfileName() {
		return profileName.getText();
		
	}

	public String veryfyKiteHomeUserId() {
		return menuButton.getText();
	}
	
	public boolean verifyMenuButtonStatus() {
		return menuButton.isEnabled();
		
	}

	public void clickOnProfileMenuButton() {
		menuButton.click();
	}

	public boolean verifyLogoutButton() {
		return logoutButton.isEnabled();
	}

	public void clickOnLogoutButton() {
		logoutButton.click();
	}

}

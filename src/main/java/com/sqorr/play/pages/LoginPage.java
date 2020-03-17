package com.sqorr.play.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.sqorr.play.core.driver.SqorrMobileDriver;

public class LoginPage extends SqorrBasePage {
	
	protected SqorrMobileDriver driver;
	
	// Strings	
	public String sqorrUser = "pradeep@myorigami.co";
	public String sqorrPwd = "pradeep@26";
	
		
	// WebElements
	@FindBy (xpath="//[@id='btnLogin']") public WebElement btnLogin;
	@FindBy (xpath="//[@id='et_email_address']") public WebElement EMAIL_ADDRESS;
	@FindBy (xpath="//[@id='et_password']") public WebElement EMAIL_PASSWORD;	
	//@FindBy (xpath="//XCUIElementTypeTextField") public WebElement EMAIL_ADDRESS;
	@FindBy (xpath="//XCUIElementTypeButton[@id='tv_login']") public WebElement LOGIN;
	
	
	// Methods	
	public LoginPage(SqorrMobileDriver driver) {
		super(driver);
		this.driver=driver;		
	}	
		
	/**
	 * Login into Application
	 */
	public void Login(String EmailAddress, String password) {
				
		driver.findElementBYID("btnLogin").click();
		
		driver.findElementBYID("et_email_address").sendKeys(EmailAddress);
		driver.findElementBYID("et_password").sendKeys(password);
		
		driver.findElementBYID("tv_login").click();								
	}	
}
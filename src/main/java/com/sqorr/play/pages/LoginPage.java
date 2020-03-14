package com.sqorr.play.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sqorr.play.core.driver.SqorrMobileDriver;

public class LoginPage extends SqorrBasePage {
	
	protected SqorrMobileDriver driver;
	
	// Strings	
	public String sqorrUser = "pradeepmyorigami.co";
	public String sqorrPwd = "pradeep@26";
	
		
	// WebElements
	@FindBy (xpath="//[@id='btnLogin']") public WebElement btnLogin;
	@FindBy (xpath="//[@id='et_email_address']") public WebElement EMAIL_ADDRESS;
	@FindBy (xpath="//[@id='et_password']") public WebElement EMAIL_PASSWORD;
	
	//@FindBy (xpath="//XCUIElementTypeTextField") public WebElement EMAIL_ADDRESS;
	@FindBy (xpath="//XCUIElementTypeButton[@id='tv_login']") public WebElement LOGIN;
		
	// Methods
	
	/**
	 * Driver
	 */
	public LoginPage(SqorrMobileDriver driver) {
		super(driver);
		this.driver=driver; 
		PageFactory.initElements(driver, this);
	}
	
		
	/**
	 * Login into Application
	 */
	public void Login(String EmailAddress, String password){		
		btnLogin.click();
		
		EMAIL_ADDRESS.sendKeys(EmailAddress);
		EMAIL_PASSWORD.sendKeys(EmailAddress);
		
		LOGIN.click();	
		DefaultWait(5000);				
	}	
}
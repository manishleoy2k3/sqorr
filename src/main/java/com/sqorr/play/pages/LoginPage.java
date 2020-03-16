package com.sqorr.play.pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.sqorr.play.core.driver.SqorrMobileDriver;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

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
	
	/**
	 * Driver
	 */
	public LoginPage(SqorrMobileDriver driver) {
		super(driver);
		this.driver=driver;
		System.out.println(driver);
		System.out.println(this.driver);
		//PageFactory.initElements(driver, this);
	}
	
		
	/**
	 * Login into Application
	 */
	public void Login(String EmailAddress, String password) {
		//driver.findElement("pagerBT").click();
		
		driver.findElementBYID("btnLogin").click();
		
		driver.findElementBYID("et_email_address").sendKeys(EmailAddress);
		driver.findElementBYID("et_password").sendKeys(password);
		
		driver.findElementBYID("tv_login").click();
		
		//btnLogin.click();
		
		//EMAIL_ADDRESS.sendKeys(EmailAddress);
		//EMAIL_PASSWORD.sendKeys(EmailAddress);
		
		//LOGIN.click();	
		DefaultWait(5000);				
	}	
}
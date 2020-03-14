package com.sqorr.play.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.sqorr.play.core.driver.SqorrMobileDriver;

public class LoginPage extends SqorrBasePage {
	
	protected SqorrMobileDriver driver;

	
	// Strings 
	
	public String Welcome_Text="Welcome";
	public String sqorrUser = "pradeepmyorigami.co";
	public String sqorrPwd = "pradeep@26";
	
	public String Newuser=generateRandomString()+"@gmail.com"; 
	public String Thankyou="Thank you for being";
	
	
	
	// WebElements
	@FindBy (xpath="//XCUIElementTypeStaticText[@name='Welcome']") public WebElement WELCOME_TEXT;
	@FindBy (xpath="//XCUIElementTypeApplication[@name='DMG']//XCUIElementTypeTextField") public WebElement EMAIL_ADDRESS;
	//@FindBy (xpath="//XCUIElementTypeTextField") public WebElement EMAIL_ADDRESS;
	@FindBy (xpath="//XCUIElementTypeButton[@name='CONTINUE']") public WebElement CONTINUE;
	@FindBy (xpath = "//XCUIElementTypeApplication[@name='DMG']//XCUIElementTypeButton[2]") public WebElement TAG_BUTTON;
	@FindBy (xpath="//XCUIElementTypeButton[@name='Go']") public WebElement GO;
	
	@FindBy (xpath="//XCUIElementTypeButton[@name='START SESSION']") public WebElement START_SESSION;
	@FindBy (xpath = "//XCUIElementTypeStaticText[@name='Thank you for being']") public WebElement THANKYOU_TEXT;
	@FindBy (xpath = "//XCUIElementTypeButton[@name='show dock']") public WebElement PLUS_ICON;
	@FindBy (xpath = "//XCUIElementTypeLink[@name='Privacy Policy']") public WebElement PRIVACY_LINK;
	@FindBy (xpath= "(//XCUIElementTypeStaticText[@name='PRIVACY POLICY'])") public WebElement PRIVACY_POLICY_PAGETEXT;
	@FindBy (xpath= "//XCUIElementTypeButton[@name='close menu icon']") public WebElement CLOSE_ICON;
	@FindBy (xpath = "//XCUIElementTypeButton[@name='SETTINGS']") public WebElement SETTING_ICON;
	@FindBy (xpath= "//XCUIElementTypeStaticText[@name='Store#: 1800002']") public WebElement SETTING_PAGE_STOREID;
	@FindBy (xpath= "//XCUIElementTypeStaticText[@name='Device Name#: GSPANN_MD_132']") public WebElement SETTING_PAGE_DEVICENAME;
	
	@FindBy (xpath = "//XCUIElementTypeStaticText[contains(text(),'SETTINGS')]") public WebElement SETTING_PAGE_TEXT;
	
	@FindBy (xpath= "//XCUIElementTypeButton[@name='FEEDBACK']") public WebElement FEEDBACK_ICON;
	@FindBy (xpath= "//XCUIElementTypeStaticText[@name='Do you have any feedback?']") public WebElement FEEDBACK_PAGE_TEXT;
	@FindBy (xpath="//XCUIElementTypeButton[@name='HELP']") public WebElement HELP_ICON;
	@FindBy (xpath="//XCUIElementTypeStaticText[@name='If you are a retail employee and continue to have issues, please use']") public WebElement HELPPAGE_TEXT;
	@FindBy (xpath="//XCUIElementTypeButton[@name='Cancel']") public WebElement CANCEL_LINK_THANKUPAGE;
	@FindBy (xpath = "//XCUIElementTypeButton[@name='OK']") public WebElement OK_POPUP_BUTTON;
    @FindBy (xpath = "//XCUIElementTypeApplication[@name='DMG']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeTextView") public WebElement FEEDBACK_ENTER_TEXT;
	
	
	
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
	 * verify Cancel Link
	 */
	
	
	public void VerifyCancelLink(){
		
	String button = TAG_BUTTON.getText().trim();
	
	System.out.println(button);
	
	if(button.equalsIgnoreCase("CONTINUE")){
			
	}	
	else{		
		click(CANCEL_LINK_THANKUPAGE);
	}
	}
	
	
	/**
	 * Login into Application
	 */
	public void Login(String EmailAddress, String password){		
		EMAIL_ADDRESS.clear();
		EMAIL_ADDRESS.sendKeys(EmailAddress);
		
		CONTINUE.click();	
		DefaultWait(5000);				
	}
	
	public void StartSession(){
		click(START_SESSION);
	}

	/**
	 * Setting Page
	 */
	
	public void SettingPage_Test(){
		verifyTextOnElement(WELCOME_TEXT, Welcome_Text);
		click(PLUS_ICON);
		click(SETTING_ICON);
		
	}
	/**
	 * Privacy Policy Page
	 */
	
	public void PrivacyPolicyPage(){
		
		verifyTextOnElement(WELCOME_TEXT, Welcome_Text);
		click(PRIVACY_LINK);
		DefaultWait(10000);
		
	}
	
	/**
	 * Feedback Page Test
	 */
	
	public void FeedBackPage(){
		click(PLUS_ICON);
		click(FEEDBACK_ICON);
		
		
	}
	
	/**
	 * Help Page Test
	 */
	
	public void HelpPage(){
	
		click(PLUS_ICON);
		click(HELP_ICON);
		DefaultWait(10000);
		
		
	}
}
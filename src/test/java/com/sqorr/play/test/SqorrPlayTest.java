package com.sqorr.play.test;

import java.net.MalformedURLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;
import com.sqorr.play.pages.LoginPage;
import com.sqorr.play.test.base.SqorrPlayBaseTest;


public class SqorrPlayTest extends SqorrPlayBaseTest {
	
	//private static final Logger logger = LogManager.getLogger(SqorrPlayTest.class.getName());
	
	private LoginPage loginpage;
	
	
	@BeforeClass
	public void objectInstances() {
		System.out.println(driver);
		loginpage = new LoginPage(driver);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void beforeMethodSetUp() {
		//logger.info("***************Before Method Starts******************");
		try {
			driver.loadApplication();
			//loginpage.VerifyCancelLink();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
				
		//logger.info("***************Before Method End*********************");	 
	}
	
	@AfterMethod(alwaysRun = true)
	public void afterMethodSetUp(ITestResult result) {
		//logger.info("****************************After Method Starts*************************");
		/*
		 * if(result.getStatus() == ITestResult.FAILURE) test.log(LogStatus.FAIL,
		 * result.getTestName()); else if(result.getStatus() == ITestResult.SUCCESS)
		 * test.log(LogStatus.PASS, result.getTestName()); else test.log(LogStatus.SKIP,
		 * result.getTestName());
		 */
		//extent.endTest(test);
		//logger.info("****************************TEST CASE END****************************\n");
		//logger.info("****************************After Method End***************************");
	}
	
	/**
	 * Description: Test Method to verify successful Login
	 * @author Manish
	 */
	@Test(enabled=true, description="Method to Login to sqorr play application")
	public void sqorr_Test_Login() {
			
		loginpage.Login(loginpage.sqorrUser, loginpage.sqorrPwd);
		
		//Assert.assertTrue(verifyValue("pageView","START_SESSION_THANKU_BI"),"Start Session Events Failed For Welcome page");
		
		//logger.info("Login to sqorr application is successful");
		
	}
	
}
	

package com.sqorr.play.test;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sqorr.play.pages.LoginPage;
import com.sqorr.play.pages.PromosPage;
import com.sqorr.play.test.base.SqorrPlayBaseTest;

public class SqorrPlayTest extends SqorrPlayBaseTest {

	private static final Logger logger = LogManager.getLogger(SqorrPlayTest.class.getName());

	private LoginPage loginpage;
	private PromosPage promospage;

	/*
	 * @BeforeClass public void objectInstances() {
	 * 
	 * try { driver.loadApplication(); } catch (MalformedURLException e) { // TODO
	 * Auto-generated catch block e.printStackTrace(); }
	 * 
	 * System.out.println(driver); loginpage = new LoginPage(driver); }
	 */
	/*
	 * @BeforeMethod(alwaysRun = true) public void beforeMethodSetUp() { //
	 * logger.info("***************Before Method Starts******************"); try {
	 * driver.loadApplication(); // loginpage.VerifyCancelLink(); } catch
	 * (MalformedURLException e) { e.printStackTrace(); }
	 * 
	 * // logger.info("***************Before Method End*********************"); }
	 */

	/*
	 * @AfterMethod(alwaysRun = true) public void afterMethodSetUp(ITestResult
	 * result) { //logger.
	 * info("****************************After Method Starts*************************"
	 * );
	 * 
	 * if(result.getStatus() == ITestResult.FAILURE) test.log(LogStatus.FAIL,
	 * result.getTestName()); else if(result.getStatus() == ITestResult.SUCCESS)
	 * test.log(LogStatus.PASS, result.getTestName()); else test.log(LogStatus.SKIP,
	 * result.getTestName());
	 * 
	 * //extent.endTest(test); //logger.
	 * info("****************************TEST CASE END****************************\n"
	 * ); //logger.
	 * info("****************************After Method End***************************"
	 * ); }
	 */

	/**
	 * Description: Test Method to verify successful Login
	 * 
	 * @author Manish
	 */
	@Test(enabled = true, description = "Method to Login to sqorr play application")
	public void sqorr_Test_Login() {

		loginpage = new LoginPage(driver);
		
		loginpage.Login(loginpage.sqorrUser, loginpage.sqorrPwd);
		
		//TODO: change this Assert to verify some page element after login
		Assert.assertTrue(true);

		logger.info("Login to sqorr application is successful");
	}
	
	/**
	 * Description: Test Method to verify click on Promos tab for checking Promos
	 * 
	 * @author Manish
	 */
	@Test(enabled = false, description = "Method to click on Promos tab to check Promos in sqorr app")
	public void sqorr_Test_Promos() {

		promospage = new PromosPage(driver);
		
		promospage.CheckPromos();
		
		//TODO: change this Assert to verify some page elements on Promos page
		Assert.assertTrue(true);

		logger.info("Promo page opening is successful");
	}

}

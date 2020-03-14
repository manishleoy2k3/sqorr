package com.sqorr.play.test.base;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.sqorr.play.core.driver.SqorrMobileDriver;

public class SqorrPlayBaseTest extends SqorrBaseTest {

	static DesiredCapabilities capabilities = new DesiredCapabilities();
	public SqorrMobileDriver driver = new SqorrMobileDriver(config);
	
	private static final Logger logger = LogManager.getLogger(SqorrPlayBaseTest.class.getName());
		
	/**
	 * @throws Exception
	 *         setup function loads the driver and environment and baseUrl for the tests
	 */
	@BeforeSuite(alwaysRun = true)
	public void setUp() throws Exception {
		logger.info("********************Before Suite Starts (SqorrPlayBaseTest)****************");
		//bmProxy.proxyStart();
		//extent = new ExtentReports(System.getProperty("user.dir")+"/test-output/"+"DMG_Analytics"+"_"+config.getProperty("device")+"_"+config.getProperty("platformName")+".html",true);
		//extent.loadConfig(new File((System.getProperty("user.dir")+"/src/test/resources/environments/extent-config.xml")));
		logger.info("********************Before Suite Ends (SqorrPlayBaseTest)****************");
	}	
	
	@AfterSuite(alwaysRun = true)
	public void tearDown() throws Exception {
		logger.info("********************After Suite Starts****************");
		driver.quit();
		//extent.flush();
		//extent.close();
		//new HtmlLogger().createHtmlAnalyticsLogFile(result);
		logger.info("********************After Suite Ends****************");
	}
}

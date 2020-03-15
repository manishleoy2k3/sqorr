package com.sqorr.play.test.base;

import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.sqorr.play.core.constants.TestConstants;
import com.sqorr.play.core.utils.Config;
import com.sqorr.play.core.utils.ExcelReader;


public class SqorrBaseTest {
	//public static WebDriver driver;
	public String defaultProps = "defaultenv.properties";
	
	//public static ExtentReports extent;
	//public static ExtentTest test;
	

	public static Config config = new Config();
	private static final Logger logger = LogManager
			.getLogger(SqorrBaseTest.class.getName());

	/**
	 * @param envproperties
	 *        envproperties is the file name that is given to pom.xml that
	 *        contains the environment details that to be loaded
	 */
	@BeforeSuite(alwaysRun = true)
	@Parameters({ "envproperties" })
	public void beforeSuite(@Optional String envproperties) {
		if (!StringUtils.isEmpty(envproperties)) {
			config.loadProps(envproperties);
			logger.debug("Environment properties recieved and preparing the environment for "
					+ envproperties); 
			logger.info("EXECUTION ENVIRONMENT ------ "+config.getProperty("platformName"));
			logger.debug("Started execution with " + " " + envproperties);

		} else {
			config.loadProps(defaultProps);
			logger.debug("Environment properties are not provided by the user ... loading the default properties");
			logger.info("EXECUTION ENVIRONMENT ------ "+config.getProperty("platformName"));
			logger.debug("Started execution with " + defaultProps);
		}
	}	

	/**
	 * @throws Exception
	 */
	/*
	 * @DataProvider(name = "sqorrTestData") public Object[][]
	 * sqorrDataProvider(Method testMethod) throws Exception { String sheetName =
	 * testMethod.getName(); String filePath = "src/test/resources/" + testMethod
	 * .getDeclaringClass() .getName() .replace(TestConstants.DOT,
	 * TestConstants.FORWARD_SLASH) + ".xlsx";
	 * logger.debug("Test data is loaded from file " + filePath +
	 * " and the sheet is " + sheetName); Object[][] testObjArray =
	 * ExcelReader.getTableArray(filePath, testMethod.getName());
	 * 
	 * return (testObjArray);
	 * 
	 * }
	 */

	public String getRunningDevice() {
		config.loadProps("src/test/resources/profiles/defaultenv.properties");
		return config.getProperty("platformName").trim();
	}
	
	public void DefaultWait(int timemiliseconds){
		try {
			Thread.sleep(timemiliseconds);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}

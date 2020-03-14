package com.sqorr.play.core.listeners;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;


public class TestListner extends TestListenerAdapter implements ITestListener {
	private static final Logger logger = LogManager.getLogger(TestListner.class.getName());
	//private TestRail testRail = new TestRail();
	String[] testIds;

	/*
	 * private String[] qmetryID(ITestResult tr) { ITestNGMethod testNGMethod =
	 * tr.getMethod(); Method method =
	 * testNGMethod.getConstructorOrMethod().getMethod();
	 * testNGMethod.getDescription(); TestRailID testAnnotation = (TestRailID)
	 * method.getAnnotation(TestRailID.class); if (testAnnotation != null) return
	 * testAnnotation.id(); else return null; }
	 */

	@Override
	public void onTestStart(ITestResult tr) {
		/*
		 * testIds = qmetryID(tr); for (String test : testIds) logger.info("[TC-" + test
		 * + " ------ Executing " + tr.getMethod().getMethodName() + "]" +
		 * "\tDESCRIPTION: " + tr.getMethod().getDescription());
		 */
	}

	@Override
	public void onTestSuccess(ITestResult tr) {
		/*
		 * testIds = qmetryID(tr); for (String test : testIds) {
		 * logger.info("[TEST PASSED ------ Test case: " + test + " passed]"); try {
		 * //testRail.testRail(1, test.replace("C", "")); } catch (Throwable e) {
		 * e.printStackTrace(); } }
		 */
	}

	@Override
	public void onTestFailure(ITestResult tr) {
		/*
		 * testIds = qmetryID(tr); Throwable cause = tr.getThrowable(); for (String test
		 * : testIds) { try { //testRail.testRail(5, test.replace("C", "")); } catch
		 * (Throwable e) { e.printStackTrace(); } if (null != cause) {
		 * logger.info("[TEST FAILED ------ Test case: " + test + " failed || " +
		 * cause.getMessage() + "]"); } else
		 * logger.info("[TEST FAILED ------ Test case: " + test + " failed]"); }
		 */
	}

	@Override
	public void onTestSkipped(ITestResult tr) {
		/*testIds = qmetryID(tr);
		for (String test : testIds)
			logger.info("[TEST SKIPPED ------ Test case: " + test + " skipped]");*/
	}
}

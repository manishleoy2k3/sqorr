package com.sqorr.play.core.utils;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

// implement IRetryAnalyzer interface
public class Retry implements IRetryAnalyzer{

	private static final Logger logger = LogManager.getLogger(Retry.class.getName());
	// set counter to 0
	static int minretryCount=0;

    // set maxcounter value. Number of times the testcase will execute once it is failed
	static int maxretryCount=1;
	@Override
	public boolean retry(ITestResult result) {
		// this will run until max count completes if test pass within this frame it will come out of for loop
		if(minretryCount<maxretryCount){
			// print the test name for log purpose
			logger.info("Testcase \""+result.getName()+"\" Failed");

			// print the counter value
			logger.info("Retrying Testcase. Retry Count: "+(minretryCount+1));

			// increment counter each time by 1
			minretryCount++;
			return true;
		}
		return false;
	}
}

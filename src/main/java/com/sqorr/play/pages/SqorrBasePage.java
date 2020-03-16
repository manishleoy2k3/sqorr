package com.sqorr.play.pages;

import java.util.Random;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.sqorr.play.core.driver.SqorrMobileDriver;
import com.sqorr.play.core.utils.Config;
import com.sqorr.play.core.driver.SqorrMobileDriver;

/**
 * @author Manish
 * SqorrBasePage is the super class for all the page classes
 */
public class SqorrBasePage extends SqorrMobileDriver{
	
	public WebDriver driver;
	public final String CHAR_LIST = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	public final int RANDOM_STRING_LENGTH = 4;
	  
	private static final Logger logger = LogManager.getLogger(SqorrBasePage.class.getName());

	public SqorrBasePage(WebDriver driver) {
		//super(driver);
	}
	
	public WebDriver getWebdriver(){
		return driver;
	}

	public void DefaultWait(int timemiliseconds){
		try {
			Thread.sleep(timemiliseconds);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	   public int getRandomNumber() {
	        int randomInt = 0;
	        Random randomGenerator = new Random();
	        randomInt = randomGenerator.nextInt(CHAR_LIST.length());
	        if (randomInt - 1 == -1) {
	            return randomInt;
	        } else {
	            return randomInt - 1;
	        }
	    }
	
	   public String generateRandomString(){
	         
	        StringBuffer randStr = new StringBuffer();
	        for(int i=0; i<RANDOM_STRING_LENGTH; i++){
	            int number = getRandomNumber();
	            char ch = CHAR_LIST.charAt(number);
	            randStr.append(ch);
	        }
	        return randStr.toString();
	    }
	     
}

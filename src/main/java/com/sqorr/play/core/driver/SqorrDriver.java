package com.sqorr.play.core.driver;

import java.net.MalformedURLException;

public interface SqorrDriver {

	/**
	 * @author Manish 
	 * SqorrDriver enforces subclasses to implement loadApplicaiton method
	 * @throws MalformedURLException 
	 */
	
	public void loadApplication() throws MalformedURLException;
	
}
 
package com.sqorr.play.pages;

import com.sqorr.play.core.driver.SqorrMobileDriver;

public class PromosPage extends SqorrBasePage{

	protected SqorrMobileDriver driver;
	
	public PromosPage(SqorrMobileDriver driver) {
		super(driver);
		this.driver=driver;
	}

	/**
	 * Check Promos tab
	 */
	public void CheckPromos() {
		driver.findElementBYID("promotxt").click();								
	}
}

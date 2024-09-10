package com.test.testcases;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.test.pages.SendToSales;

public class TC004SendToSales {
	
	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void run()
	{
		logger.info("TC004SendToSales test case execution started.");
		new SendToSales()
		.navigateToDashboard()
		.mapRequest()
		.clickActiveRequest()
		.clickSentToSales() 
		.sendEmailToSales();
		logger.info("TC004SendToSales test case execution ended.");	 
	}

}

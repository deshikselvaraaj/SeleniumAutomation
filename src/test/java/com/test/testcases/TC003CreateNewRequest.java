package com.test.testcases;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import com.test.pages.CreateNewRequest;

public class TC003CreateNewRequest {
	
	private static Logger logger = LogManager.getLogger();

	@Test
	public void run() 
	{
		logger.info("TC003CreateNewRequest test case execution started.");

		new CreateNewRequest()		
		.createNewRequest()	
		.enterRequestInformation()
		.enterSalesInformation()
		.clickAddMateraialButton()
		.enterComments()
		.addAttachements()
		.submitToVendors()
		.sendEmail()
		.saveRequest();
		new CreateNewRequest();
		logger.info("TC003CreateNewRequest test case execution ended.");
	}
	
}

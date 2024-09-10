package com.test.testcases;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.test.pages.SubmitDraft;

public class TC005SubmitDraft {
	
	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void run() {
		logger.info("TC005SubmitDraft test case execution started.");
		new SubmitDraft().
		createNewRequest()
		.clickSaveAsDraft()
		.navigateToDashboard()
		.clickDraftRequest()
		.enterRequestInformation()
		.enterSalesInformation()
		.clickAddMateraialButton()
		.addAttachements()
		.submitToVendors()
		.sendEmail()
		.saveRequest();
		logger.info("TC005SubmitDraft test case execution ended.");
	}

}

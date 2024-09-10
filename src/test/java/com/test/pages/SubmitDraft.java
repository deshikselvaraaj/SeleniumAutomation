package com.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.wrappers.Wrappers;

public class SubmitDraft extends Wrappers {
	
	private static Logger logger = LogManager.getLogger();

	public SubmitDraft createNewRequest() {
		logger.info("createNewRequest method execution started.");

		new SendToSales().navigateToDashboard();
		new CreateNewRequest().createNewRequest();
		
		logger.info("createNewRequest method execution ended.");
		return this;
	}

	public SubmitDraft clickSaveAsDraft() {
		logger.info("clickSaveAsDraft method execution started.");

		clickByXpath("//button[normalize-space()='Save as draft']");
		acceptMendixAlert();
		acceptMendixAlert();

		logger.info("clickSaveAsDraft method execution ended.");
		return this;
	}

	public SubmitDraft navigateToDashboard() {
		logger.info("navigateToDashboard method execution started.");

		new SendToSales().navigateToDashboard();
		
		logger.info("navigateToDashboard method execution ended.");
		return this;
	}

	public SubmitDraft clickDraftRequest() {
		logger.info("clickDraftRequest method execution started.");

		threadSleep(2);
		clickByXpath("//span[text()='Draft']");
		
		logger.info("clickDraftRequest method execution ended.");
		return this;
	}

	public SubmitDraft enterRequestInformation() {
		logger.info("enterRequestInformation method execution started.");

		new CreateNewRequest().enterRequestInformation();
		
		logger.info("enterRequestInformation method execution ended.");
		return this;
	}

	public SubmitDraft enterSalesInformation() {
		logger.info("enterSalesInformation method execution started.");

		new CreateNewRequest().enterSalesInformation();
		
		logger.info("enterSalesInformation method execution ended.");
		return this;
	}

	public SubmitDraft clickAddMateraialButton() {
		logger.info("clickAddMateraialButton method execution started.");
		
		new CreateNewRequest().clickAddMateraialButton();
		
		logger.info("clickAddMateraialButton method execution ended.");
		return this;
	}

	public SubmitDraft addAttachements() {
		logger.info(" method execution started.");
		
		new CreateNewRequest().addAttachements();
		return this;
	}

	public SubmitDraft submitToVendors() {
		logger.info("submitToVendors method execution started.");
		
		new CreateNewRequest().submitToVendors();
		
		logger.info("submitToVendors method execution ended.");
		return this;
	}

	public SubmitDraft sendEmail() {
		logger.info("sendEmail method execution started.");
		
		new CreateNewRequest().sendEmail();
		
		logger.info("sendEmail method execution ended.");
		return this;
	}

	public SubmitDraft saveRequest() {
		logger.info("saveRequest method execution started.");
		
		new CreateNewRequest().saveRequest();
		
		logger.info("saveRequest method execution ended.");
		return this;
	}

}

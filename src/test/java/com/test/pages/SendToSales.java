package com.test.pages;

import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.wrappers.Wrappers;

public class SendToSales extends Wrappers {
	
	private static Logger logger = LogManager.getLogger();

	public SendToSales navigateToDashboard() {
		logger.info("navigateToDashboard method execution started.");
		clickByXpath("//span[@class='glyphicon glyphicon-home']");
		logger.info("navigateToDashboard method execution ended.");
		return this;
	}

	public SendToSales mapRequest() {
		logger.info("mapRequest method execution ended.");
		threadSleep(2);
		clickByXpath("//button[normalize-space()='Map Request']");
		clickByXpath("//span[@role='presentation']");
		new CreateNewRequest();
		String request = CreateNewRequest.RequestID;
		System.out.println("Request : " + request);
		enterByXpath("//input[@role='searchbox']", request);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		clickByXpath("//button[normalize-space()='Ok']");
		acceptMendixAlert();
		logger.info("mapRequest method execution ended.");
		return this;
	}

	public SendToSales clickActiveRequest() {
		logger.info("clickActiveRequest method execution ended.");
		threadSleep(2);
		clickByXpath("//span[text()='Active']");
		System.out.println("Active Request clicked successfully");
		logger.info("clickActiveRequest method execution ended.");
		return this;
	}

	public SendToSales clickSentToSales() {
		logger.info("clickSentToSales method execution ended.");
		clickByXpath("//a[text()='Sent to Sales']");
		System.out.println("Sent to Sales clicked successfully");
		logger.info("clickSentToSales method execution ended.");
		return this;
	}

	public SendToSales sendEmailToSales() {
		logger.info("sendEmailToSales method execution ended.");
		clickByXpath("//button[normalize-space()='Send email to sales']");
		new CreateNewRequest().sendEmail();
		logger.info("sendEmailToSales method execution ended.");
		return this;
	}

	public SendToSales validateRequest() {
		logger.info("validateRequest method execution ended.");
		verifySelectedDropdownValue("//select[contains(@id,'AllRequest.dropDown7')]", "Sent to Sales");
		logger.info("validateRequest method execution ended.");
		return this;
	}

}

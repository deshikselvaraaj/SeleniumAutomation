package com.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.test.wrappers.Wrappers;

public class RequestCounts extends Wrappers {
	
	private static Logger logger = LogManager.getLogger();

	public RequestCounts requestCounts()
	{

		logger.info(" method execution started.");
		
		logger.info("Count of Requests:");
		WebElement allCounts = driver.findElement(By.xpath("//span[text()='All']"));
		String allCount = allCounts.findElement(By.xpath("following-sibling::span")).getText();
		logger.info("All : " + allCount);
		
		WebElement activeCounts= driver.findElement(By.xpath("//span[text()='Active']"));
		String activeCount = activeCounts.findElement(By.xpath("following-sibling::span")).getText();
		logger.info("Active : " + activeCount);
		
		WebElement draftCounts= driver.findElement(By.xpath("//span[text()='Draft']"));
		String draftCount = draftCounts.findElement(By.xpath("following-sibling::span")).getText();
		logger.info("Draft : " + draftCount);
		
		WebElement closedCounts= driver.findElement(By.xpath("//span[text()='Closed']"));
		String closedCount = closedCounts.findElement(By.xpath("following-sibling::span")).getText();
		logger.info("Closed : " + closedCount);
		
		logger.info("requestCounts method execution ended.");
		return this;
	}
	
}

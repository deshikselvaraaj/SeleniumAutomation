package com.test.testcases;

import org.testng.annotations.Test;

import com.test.wrappers.Wrappers;

public class UploadFile extends Wrappers {

	@Test
	public void run() {

		clickByXpath("//span[text()='New Request']");

		enterByXpath("//textarea[contains(@id,'textArea7')]", "Test Comment");

		
		enterByXpath("//*[@id='react-select-3-input']","Bright");

		// ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",
		// driver.findElement(By.xpath("//span[@class='guide-toc__heading']")));

	}
}

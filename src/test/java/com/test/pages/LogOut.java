package com.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.test.wrappers.Wrappers;

public class LogOut extends Wrappers {

	private static Logger logger = LogManager.getLogger();

	public void logOut() {
		logger.info("logOut method execution started.");

	//	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",driver.findElement(By.xpath("//button[normalize-space()='Open']")));


		/* Clicks the log out button */
		clickByXpath("//button[@class='btn mx-button mx-name-actionButton1 btn-signout btn-default']//img");
		/* Clicks OK in the confirmation pop up */
		acceptMendixAlert();

		logger.info("logOut method execution ended.");

	}

}

package com.test.testcases;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.test.pages.LogOut;

public class TC002LogOut {
	
	private static Logger logger = LogManager.getLogger();

	@Test(enabled = true)
	public void run()
	{
		logger.info("TC002LogOut test case execution started.");
		new LogOut().logOut();
		logger.info("TC002LogOut test case execution ended.");
	}
}

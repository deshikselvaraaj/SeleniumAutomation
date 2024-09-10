package com.test.testcases;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.test.pages.RequestCounts;

public class TC006RequestCounts {
	
	private static Logger logger = LogManager.getLogger();
	
	@Test
	public void run()
	{
		logger.info("TC006RequestCounts test case execution started.");
		new RequestCounts().requestCounts();
		logger.info("TC006RequestCounts test case execution ended.");
	}

}

package com.utils.tests;

import java.io.FileInputStream; 
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class Utilities{
	
	private static Logger logger = LogManager.getLogger();
	protected Properties properties = new Properties();
	
	
	protected Utilities()
	{
		
		InputStream input = null;

		try {
			input = new FileInputStream("src\\main\\resources\\config.properties");
			properties.load(input);
			logger.info("Properties file load completed.");
		} catch (IOException ex) {
			logger.error("An exception occurred in locating properties file : " + ex.getMessage());
		} finally {
			if (input != null) {
				try {
					input.close();
					logger.info("FileInputStream closed");
				} catch (IOException ex) {
					logger.error("An exception occurred in closing FileInputStream : " + ex.getMessage());
				}
			}
		}
	}
	
	public void threadSleep(int second) {
		try {
			logger.info("Thread sleep initiated for " + second +"s.");
			Thread.sleep(second * 1000);
		} catch (InterruptedException ex) {
			logger.error("An exception occured in Thread.sleep() : " + ex.getMessage() );
		}
	}
	
	
	/**
	 * This method will return date based on the formatted provided as below
	 * "MM/dd/yyyy", "dd/MM/yyyy", "yyyy-MM-dd"
	 * 
	 * @param format
	 * @return
	 */
	public String getCurrentDate(String format) {
		logger.info("System is fetching the current date and time...");
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat(format);
		String formattedDate = formatter.format(date);
		logger.info("Returned the current system date and time.");
		return formattedDate;
	}
}

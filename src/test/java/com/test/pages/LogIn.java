package com.test.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.test.wrappers.Wrappers;

public class LogIn extends Wrappers {
	private static Logger logger = LogManager.getLogger();
	
	public LogIn enterUsernamePassword() {
		logger.info("enterUsernamePassword method execution started.");
		enterByXpath("//input[@id='usernameInput']", properties.getProperty("USERNAME"));
		enterByXpath("//input[@id='passwordInput']", properties.getProperty("PASSWORD"));
		logger.info("enterUsernamePassword method execution ended.");
		return this;
	}

	public LogIn showPassword() {
		logger.info("showPassword method execution started.");
		clickByXpath("//span[@class='glyphicon glyphicon-eye-close']");
		logger.info("showPassword method execution ended.");
		return this;
	}

	public LogIn hidePassword() {
		logger.info("hidePassword method execution started.");
		clickByXpath("//span[@class='glyphicon glyphicon-eye-open']");
		logger.info("hidePassword method execution ended.");
		return this;
	}
	
	public LogIn signIn()
	{
		logger.info("signIn method execution started.");
		clickByXpath("//button[@id='loginButton']");
		logger.info("signIn method execution ended.");
		return this;
	}
	
	public LogIn verifyLogIn()
	{
		logger.info("verifyLogIn method execution started.");
		verifyTextByXpath("//span[@class='mx-text mx-name-text3 lay-top__hd']", "Dashboard");
		logger.info("verifyLogIn method execution ended.");
		return this;
	}

}

package com.test.testcases;

import org.apache.logging.log4j.LogManager;   
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;

import com.test.pages.LogIn;

public class TC001LogIn {
	
	private static Logger logger = LogManager.getLogger();

	@Test
	public void run() {
		logger.info("TC001LogIn test case execution started.");
		new LogIn()
		.enterUsernamePassword()
		.showPassword()
		.hidePassword()
		.signIn()
		.verifyLogIn();
		logger.info("TC001LogIn test case execution ended.");
		//.quickSignInAsAdmin();
		//.quickSignInAsGuest();
		//.quickSignInAsManager();
		//.quickSignInAsUser();
		//.logInWithSSO();
		
		

		
	}
	
	
}
//
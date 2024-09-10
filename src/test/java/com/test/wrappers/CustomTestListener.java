package com.test.wrappers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.utils.tests.ExcelReportGeneration;
import com.utils.tests.ExtentReportsConfig;

public class CustomTestListener extends ExcelReportGeneration implements ITestListener {

	private static Logger logger = LogManager.getLogger();
	
	ExtentReports reports = ExtentReportsConfig.reportConfig();
	ExtentTest test;
	WebDriver driver;
	

	@Override
	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		ITestListener.super.onStart(context);
				
	}

	@Override
	public void onTestStart(ITestResult result) {
		logger.info("CustomTestListener onTestStart method overriding started.");
		
		//Extent Report
		test = reports.createTest(result.getMethod().getMethodName());		
		
		logger.info("CustomTestListener onTestStart method overriding started.");

	}

	@Override
	public void onTestSuccess(ITestResult result) {
		logger.info("onTestSuccess method overriding started.");
		
		//Start of update excel report code
		String testName = result.getMethod().getRealClass().getSimpleName();
		boolean isPassed = result.isSuccess();
		logger.info("Test Name : " + testName + "\tStatus : " + isPassed);
		updateExcel(testName, isPassed, "NA");
		//End of update excel report code
		
		//Extent Report
		test.log(Status.PASS, "Test Passed");
		
		logger.info("onTestSuccess method overriding ended.");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		logger.info("onTestFailure method overriding started.");

		//Start of update excel report code
		String exceptionName = null;

		String testName = result.getMethod().getRealClass().getSimpleName();
		boolean isPassed = result.isSuccess();
		logger.info("Test Name : " + testName + "\tStatus : " + isPassed);

		Throwable throwable = result.getThrowable();
		if (throwable != null)
			exceptionName = throwable.getClass().getSimpleName();

		updateExcel(testName, isPassed, exceptionName);
		//End of update excel report code
		
		
		//Extent Report
		test.fail(result.getThrowable());
		
		logger.info("onTestFailure method overriding ended.");
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
	}

	@Override
	public void onTestFailedWithTimeout(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestFailedWithTimeout(result);
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		// TODO Auto-generated method stub
		ITestListener.super.onTestSkipped(result);
	}

	@Override
	public void onFinish(ITestContext context) {
		logger.info("CustomTestListener onFinish method overriding started.");
		// Test execution finished
		
		//Extent Report
		reports.flush();
		
		logger.info("CustomTestListener onFinish method overriding ended.");
	}

}

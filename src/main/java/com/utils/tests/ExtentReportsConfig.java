package com.utils.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportsConfig {
	
	static ExtentReports reports;
	
	public static ExtentReports reportConfig()
	{
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("Automation Test Report");
		reporter.config().setDocumentTitle("Test Results");
	
		reports=new ExtentReports();
		reports.attachReporter(reporter);
		reports.setSystemInfo("Tester", "MX Techies");
		return reports;
	}

}

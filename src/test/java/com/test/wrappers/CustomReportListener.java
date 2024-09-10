package com.test.wrappers;

import org.apache.logging.log4j.LogManager; 
import org.apache.logging.log4j.Logger;
import org.testng.IReporter;
import org.testng.ISuite;
import org.testng.reporters.EmailableReporter;
import org.testng.xml.XmlSuite;

import com.utils.tests.SendEmail;

import java.io.IOException;
import java.util.List;


/*An automated email feature is present as a part of this framework that will send the test report email.
* Usually the report will get generated only after the suite execution is completed. Hence, the email feature
* is sending the report of the previous test run. This class is used to force the system to generate the report
* located in "\\test-output\\emailable-report.html" before the suite execution completes.
*/
public class CustomReportListener extends EmailableReporter implements IReporter {

	private static Logger logger = LogManager.getLogger();
	
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {
        // Perform any custom operations on the report before it is generated
    	logger.info("CustomReportListener execution started.");
    	logger.info("Overriding generateReport method.");
    	
    	
        // Call the super method to generate the emailable report
        super.generateReport(xmlSuites, suites, outputDirectory);
        logger.info("Forcing system to generate reports before the complete execution ends.");

        SendEmail sm= new SendEmail();
    	try {
    		logger.info("calling method to send email.");
			sm.sendEmail();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error(e.getStackTrace());
		}
    	
    }
}


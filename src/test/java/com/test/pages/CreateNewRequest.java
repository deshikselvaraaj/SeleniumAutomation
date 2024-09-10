package com.test.pages;

import java.awt.event.KeyEvent;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import com.test.wrappers.Wrappers;

public class CreateNewRequest extends Wrappers {

	private static Logger logger = LogManager.getLogger();
	public static String RequestID;
	
	public CreateNewRequest createNewRequest() {
		// New Request
		logger.info("createNewRequest method execution started.");
		clickByXpath("//span[text()='New Request']");
		verifyTextByXpath("//label[contains(.,'Request Name')]", "Request Name");
		
		logger.info("createNewRequest method execution ended.");
		return this;
	}

	public CreateNewRequest enterRequestInformation() {
		logger.info("enterRequestInformation method execution started.");
		
		// Request Name
		WebElement requestName = driver.findElement(By.xpath("//input[contains(@id,'textBox2')]"));
		if (requestName.getAttribute("value").isEmpty()) {
			enterByXpath("//input[contains(@id,'textBox2')]", "Test Request");
		}

		// RequestNotes
		enterByXpath("//textarea[contains(@id,'textArea1')]", "Test Request Notes");
		// Is special packaging required?
		clickByXpath("//input[contains(@id,'checkBox2')]");
		// Is there country of origin restrictions?
		clickByXpath("//input[contains(@id,'radioButtons4') and contains(@id,'_1')]");
		
		logger.info("enterRequestInformation method execution ended.");
		return this;
	}

	public CreateNewRequest enterSalesInformation() {
		logger.info("enterSalesInformation method execution started.");
		// Sales Name
		enterByXpath("//input[contains(@id,'textBox8')]", "Test Sales");
		// Email ID
		enterByXpath("//input[contains(@id,'textBox11')]", "test@test.com");

		logger.info("enterSalesInformation method execution ended.");
		return this;
	}

	public CreateNewRequest clickAddMateraialButton() {
		logger.info("clickAddMateraialButton method execution started.");
		// Add Material Button
		clickByXpath("//button[normalize-space()='Add Material']");
		// Description in Add Material
		enterByXpath("//textarea[contains(@id,'Material_NewPage.textArea1')]", "Test Description");
		// Quantity in Add Material
		enterByXpath("//input[contains(@id,'NewPage.textBox4')]", "4");
		// Unit of Measure in Add Material
		enterByXpath("//input[contains(@id,'NewPage.textBox20')]", "kg/m");
		// SKU in Add Material
		enterByXpath("//input[contains(@id,'NewPage.textBox3')]", "3");
		// Save button in Add Material
		clickByXpath("//button[@class='btn mx-button mx-name-actionButton1 btn-submit pull-right btn-success']");

		logger.info("clickAddMateraialButton method execution ended.");
		return this;
	}

	public CreateNewRequest enterComments() {
		logger.info("enterComments method execution started.");
		// Type to Search in Comments
		clickByXpath("//div[@class='PrefixCustomDropdown__placeholder css-1wa3eu0-placeholder']");
		robot.keyPress(KeyEvent.VK_DOWN);
		robot.keyRelease(KeyEvent.VK_DOWN);
		threadSleep(3);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		// Leave you comments
		enterByXpath("//textarea[contains(@id,'textArea7')]", "Test Comment");
		// Post Button
		clickByXpath("//button[normalize-space()='Post']");
		logger.info("enterComments method execution ended.");
		return this;
	}

	public CreateNewRequest addAttachements() {
		logger.info("addAttachements method execution started.");
		// Attachments
		clickByXpath("//button[normalize-space()='Attachments']");
		// Drop the file here or click to select
		uploadFile("//div[@class='filedropper__dropzone']", "src\\test\\sample.pdf");
		threadSleep(2);
		logger.info("addAttachements method execution ended.");
		return this;
	}

	public CreateNewRequest submitToVendors() {
		logger.info("submitToVendors method execution started.");
		// Submit to Vendors
		clickByXpath("//button[normalize-space()='Submit to vendors']");
		acceptMendixAlert();
		logger.info("submitToVendors method execution ended.");
		return this;
	}

	public CreateNewRequest sendEmail() {
		logger.info("sendEmail method execution started.");
		// To address in New Email - Send to Vendor
		threadSleep(2);
		enterByXpath("//input[contains(@id,'ComposeEmail.textBox5')]", "deshik.s@mxtechies.com");

		// Email content in New Email - Send to Vendor
		enterByXpath(
				"//div[contains(@title,'Rich Text Editor, html_editor_CKEditorForMendix_widget_CKEditorForMendix')]",
				"This is a test email");

		// Add Material Button in Email
		clickByXpath("//button[normalize-space()='Add Material']");
		// Click first checkbox in Add Material
		clickByXpath("//input[contains(@id,'checkBox1')]");
		// Submit button in Add Material
		clickByXpath("//button[normalize-space()='Submit']");

		// Add Signature in Email
		clickByXpath("//button[normalize-space()='Add Signature']");
		// OK button in Information pop up
		acceptMendixAlert();
		
		//Add Mapped Email
		try {
		WebElement addMappedEmailButton = driver.findElement(By.xpath("//button[normalize-space()='Add Mapped Email']"));
		if(addMappedEmailButton.isDisplayed())
		{
			clickByXpath("//button[normalize-space()='Add Mapped Email']");
			clickByXpath("//input[contains(@id,'MappedMail_View.checkBox1')]");
			clickByXpath("//button[normalize-space()='Submit']");
			threadSleep(5);
		}
		}catch(NoSuchElementException ex)
		{
			logger.error("Add Mapped Email Button is not present. An exception occured. "+ ex.getMessage());
		}
		
		// Send Button - New Email - Send to Vendor
		clickByXpath("//button[normalize-space()='Send']");

		// Confirmation pop up
		acceptMendixAlert();

		logger.info("sendEmail method execution ended.");
		return this;
	}

	public CreateNewRequest saveRequest() {
		logger.info("saveRequest method execution started.");
		//Fetch request ID
		WebElement requestDetails = driver.findElement(By.xpath("//span[contains(@class,'req-t1')]"));
		String[] requestID = requestDetails.getText().split("\\ ");
		RequestID = requestID[0].trim();
		
		// Save button in View Request
		clickByXpath("//button[normalize-space()='Save']");

		// Save confirmation in View Request
		acceptMendixAlert();		

		// Information pop up
		acceptMendixAlert();

		logger.info("saveRequest method execution ended.");
		return this;
	}

}

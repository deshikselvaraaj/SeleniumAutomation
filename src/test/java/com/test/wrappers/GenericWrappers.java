package com.test.wrappers;

import java.awt.AWTException;  
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.utils.tests.ExcelColumnClear;
import com.utils.tests.Utilities;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericWrappers extends Utilities{

	public static WebDriver driver;
	public static Robot robot;
	private static Logger logger = LogManager.getLogger();

	@BeforeSuite
	public void genericWrappers() {
		logger.info("Test Suite execution started.");

		// Initiate browser		
		String browser = properties.getProperty("BROWSER");
		logger.info(properties.getProperty("BROWSER") +" browser initiation started...");
		switch (browser.toLowerCase()) {
		case "chrome":
			WebDriverManager.chromedriver().setup();
			ChromeOptions opt = new ChromeOptions();
			opt.addArguments("headless");
			// driver = new ChromeDriver(opt);
			driver = new ChromeDriver();
			logger.info("Chrome browser invoked.");
			break;
		case "firefox":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			logger.info("Firefox browser invoked.");
			break;
		case "edge":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			logger.info("Edge browser invoked");
			break;
		default:
			System.out.println("Invalid browser name. " + "Starting Chrome browser.");
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.warn("Invalid brower argument passed. Chrome browser invoked.");
			break;
		}
		driver.manage().window().maximize();
		logger.info("Browser window maximized.");
		
		driver.manage().deleteAllCookies();


		// Invoke application
		driver.get(properties.getProperty("URL"));
		logger.info("Opening URL " + properties.getProperty("URL"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Instantiate Robot class
		try {
			robot = new Robot();
		} catch (AWTException ex) {
			logger.error("An exception occurred in instantiating Robot class :  " + ex.getMessage());
		}
		
		//To clear the test status in excel sheet
		ExcelColumnClear ecc = new ExcelColumnClear();
		ecc.clearColumn();

	}

	@AfterSuite(enabled = false)
	public void closeBrowser() {

		threadSleep(2);
		driver.quit();
		logger.info("Closing browser.");
		logger.info("Test Suite execution ended.");
		

	}


	public Duration explicitWait(int seconds) {
		Duration time = Duration.ofSeconds(seconds);
		return time;
	}

	/**
	 * This method will wait until the element is visible for specified time
	 * 
	 * @param element
	 * @param time
	 * @author Deshik - MX Techies
	 */
	public void waitForElementToBeVisible(WebElement element, int seconds) {
		Duration time = Duration.ofSeconds(seconds);
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	/**
	 * This method will wait until the element is clickable for specified time
	 * 
	 * @param element
	 * @param time
	 * @author Deshik - MX Techies
	 */
	public void waitForElementToBeClickable(WebElement element, int seconds) {
		logger.info("waitForElementToBeClickable aruguments received - WebElement : " + element + ", Seconds : " + seconds);
		Duration time = Duration.ofSeconds(seconds);
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(element));
		logger.info("Waiting explicitly for element to be clickable.");

	}

	/**
	 * This method will wait until the element is selected for specified time
	 * @param element
	 * @param seconds
	 * @author Deshik - MX Techies
	 */
	public void waitForElementToBeSelected(WebElement element, int seconds) {
		logger.info("waitForElementToBeSelected aruguments received - WebElement : " + element + ", Seconds : " + seconds);
		Duration time = Duration.ofSeconds(seconds);
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeSelected(element));
		logger.info("Waiting explicitly for element to be clickable.");

	}

	/**
	 * Tbis method will wait for the alert for specified time
	 * 
	 * @param time
	 * @author Deshik - MX Techies
	 */
	public void waitForAlert(int seconds) {

		WebDriverWait wait = new WebDriverWait(driver, explicitWait(seconds));
		wait.until(ExpectedConditions.alertIsPresent());
		logger.info("Waiting for an alert to be present.");
	}

	/**
	 * This method will click the element using xpath
	 * 
	 * @param xpathLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByXpath(String xpathLocator) {
		logger.info("clickByXpath argument received - Xpath : " + xpathLocator);
		WebElement clickable = driver.findElement(By.xpath(xpathLocator));
		waitForElementToBeClickable(clickable, 10);
		clickable.click();
		logger.info("Clicked "+xpathLocator + " by xpath.");
		/*
		 * waitForElementToBeSelected(clickable, 10);
		 * Assert.assertTrue(clickable.isSelected(), "The element is not clicked.");
		 */

	}

	/**
	 * This method will click the element using id
	 * 
	 * @param idLocator
	 * @author Deshik - MX Techies
	 */
	public void clickById(String idLocator) {
		logger.info("clickById argument received - Id : " + idLocator);
		WebElement clickable = driver.findElement(By.id(idLocator));
		waitForElementToBeVisible(clickable, 10);
		clickable.click();
		logger.info("Clicked " + idLocator + " by Id.");
		

	}

	/**
	 * This method will click the element using CSS selector
	 * 
	 * @param cssLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByCssSelector(String cssLocator) {
		logger.info("clickByCssSelector argument received - CSSLocator : " +cssLocator);
		WebElement clickable = driver.findElement(By.cssSelector(cssLocator));
		waitForElementToBeVisible(clickable, 10);
		clickable.click();
		logger.info("Clicked " + cssLocator + " by CSSLocator.");
	}

	/**
	 * This method will click the element using link text
	 * 
	 * @param linkTextLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByLinkText(String linkTextLocator) {
		logger.info("clickByLinkText argument received - Link Text : " +linkTextLocator);
		WebElement clickable = driver.findElement(By.linkText(linkTextLocator));
		waitForElementToBeClickable(clickable, 10);
		clickable.click();
		logger.info("Clicked " + linkTextLocator + " by Link Text.");

	}

	/**
	 * This method will click the element using class name
	 * 
	 * @param classNameLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByClassName(String classNameLocator) {
		WebElement clickable = driver.findElement(By.className(classNameLocator));
		waitForElementToBeVisible(clickable, 10);
		clickable.click();

	}

	/**
	 * This method will click the element using partial link text
	 * 
	 * @param partialLinkTextLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByPartialLinkText(String partialLinkTextLocator) {

		WebElement clickable = driver.findElement(By.partialLinkText(partialLinkTextLocator));
		waitForElementToBeVisible(clickable, 10);
		clickable.click();
		/*
		 * waitForElementToBeSelected(clickable, 10);
		 * Assert.assertTrue(clickable.isSelected(), "The element is not clicked.");
		 */

	}

	/**
	 * This method will click the element using tag name
	 * 
	 * @param tagNameLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByTagName(String tagNameLocator) {
		WebElement clickable = driver.findElement(By.tagName(tagNameLocator));
		waitForElementToBeVisible(clickable, 10);
		clickable.click();
		}

	/**
	 * This method will click the element using name
	 * 
	 * @param nameLocator
	 * @author Deshik - MX Techies
	 */
	public void clickByName(String nameLocator) {
		WebElement clickable = driver.findElement(By.name(nameLocator));
		waitForElementToBeVisible(clickable, 10);
		clickable.click();
	}

	/**
	 * This method will select the dropdown value using xpath
	 * 
	 * @param xpath
	 * @param value
	 * @author Deshik - MX Techies
	 */
	public void selectDropdownByXpath(String xpath, String value) {
		logger.info("selectDropdownByXpath arguments received - Xpath : "+xpath+", Value : "+value + ".");
		WebElement dropdown = driver.findElement(By.xpath(xpath));
		waitForElementToBeClickable(dropdown, 10);
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		logger.info("Selected value " + value + " from dropdown");
	}

	/**
	 * This method will select the dropdown value using id
	 * 
	 * @param id
	 * @param value
	 * @author Deshik - MX Techies
	 */
	public void selectDropdownById(String id, String value) {
		logger.info("selectDropdownById arguments received - Xpath : "+id+", Value : "+value + ".");
		WebElement dropdown = driver.findElement(By.xpath(id));
		waitForElementToBeClickable(dropdown, 10);
		Select select = new Select(dropdown);
		select.selectByVisibleText(value);
		waitForElementToBeSelected(dropdown, 10);
		logger.info("Selected value " + value + " from dropdown");
	}

	/**
	 * This method will verify the expected value with the dropdown selected value
	 * @param xpath
	 * @param value
	 * @author Deshik - MX Techies
	 */
	public void verifySelectedDropdownValue(String xpath, String value) {
		logger.info("verifySelectedDropdownValue arguments received - Xpath : "+xpath+", Value : "+value + ".");
		WebElement dropdown = driver.findElement(By.xpath(xpath));
		waitForElementToBeClickable(dropdown, 10);
		Select select = new Select(dropdown);
		String actualSelectedValue = select.getFirstSelectedOption().getText();
		Assert.assertEquals(actualSelectedValue, value, "Selected value is not as expected");
		logger.info("Verified actual value + " +actualSelectedValue+ "with expected value "+value+".");
	}

	/**
	 * This method will enter the value to the text field using id
	 * 
	 * @param idValue - id of the webelement
	 * @param data    - The data to be sent to the webelement
	 * @author Deshik - MX Techies
	 */
	public void enterById(String idLocator, String data) {
		logger.info("enterById arguments received - Xpath : "+idLocator+", Value : "+data + ".");
		WebElement textbox = driver.findElement(By.xpath(idLocator));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
		logger.info("Entered value " +data+ " in " +idLocator +".");
		}

	/**
	 * This method will enter the value to the text field using xpath
	 * 
	 * @param xpathLocator
	 * @param data
	 * @author Deshik - MX Techies
	 */
	public void enterByXpath(String xpathLocator, String data) {
		logger.info("enterByXpath arguments received - Xpath : "+xpathLocator+", Value : "+data + ".");
		WebElement textbox = driver.findElement(By.xpath(xpathLocator));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
		logger.info("Entered value " +data+ " in " +xpathLocator +".");
	}

	/**
	 * This method will enter the value to the text field using name
	 * 
	 * @param nameLocator
	 * @param data
	 * @author Deshik - MX Techies
	 */
	public void enterByname(String nameLocator, String data) {
		WebElement textbox = driver.findElement(By.xpath(nameLocator));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
	}

	/**
	 * This method will enter the value to the text field using CSS
	 * 
	 * @param cssLocator
	 * @param data
	 * @author Deshik - MX Techies
	 */
	public void enterByCssLocator(String cssLocator, String data) {
		clickByCssSelector(cssLocator);
		WebElement textbox = driver.findElement(By.xpath(cssLocator));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
	}

	/**
	 * This method will enter the value to the text field using link text
	 * 
	 * @param linkTextLocator
	 * @param data
	 * @author Deshik - MX Techies
	 */
	public void enterByLinkText(String linkTextLocator, String data) {
		WebElement textbox = driver.findElement(By.xpath(linkTextLocator));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
	}

	/**
	 * This method will enter the value to the text field using partial link text
	 * 
	 * @param partialLinkTextLocator
	 * @param data
	 * @author Deshik - MX Techies
	 */
	public void enterByPartialLinkText(String partialLinkTextLocator, String data) {
		WebElement textbox = driver.findElement(By.xpath(partialLinkTextLocator));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
		}

	/**
	 * This method will enter the value to the text field using tag name
	 * 
	 * @param tagName
	 * @param data
	 * @author Deshik - MX Techies
	 */
	public void enterByTagName(String tagName, String data) {
		WebElement textbox = driver.findElement(By.xpath(tagName));
		waitForElementToBeVisible(textbox, 10);
		textbox.clear();
		textbox.sendKeys(data);
		}

	/**
	 * This method will verify the given text matches with the element text
	 * 
	 * @param xpath        - The locator of the object in xpath
	 * @param expectedText - The text to be verified
	 * @author Deshik - MX Techies
	 */
	public void verifyTextByXpath(String xpath, String expectedText) {
		logger.info("verifyTextByXpath arguments received - XPATH : " + xpath + ", Expected Text : " +expectedText + ".");
		WebElement actualText = driver.findElement(By.xpath(xpath));
		waitForElementToBeVisible(actualText, 10);
		Assert.assertTrue(actualText.getText().toLowerCase().contains(expectedText.toLowerCase()), "The expected text ("
				+ expectedText + ") is different from actual text (" + actualText.getText() + ")");
		logger.info("Verified expected text "+expectedText+ " with actual text " +actualText+".");
	}

	/**
	 * This method will verify the given text matches with the element text
	 * 
	 * @param id           - The locator of the object in id
	 * @param expectedText - The text to be verified
	 * @author Deshik - MX Techies
	 */
	public void verifyTextById(String id, String expectedText) {
		logger.info("verifyTextById arguments received - Id : " + id + ", Expected Text : " +expectedText + ".");
		WebElement actualText = driver.findElement(By.id(id));
		waitForElementToBeVisible(actualText, 10);
		Assert.assertTrue(actualText.getText().contains(expectedText),
				"The expected text is different from actual text");
		logger.info("Verified expected text "+expectedText+ " with actual text " +actualText+".");
	}

	/**
	 * This method will upload a file in the provided xpath. This works for File
	 * Dropper Mendix widget which may not work for others
	 * 
	 * @param xpath
	 * @param filePath
	 * @author Deshik - MX Techies
	 */
	public void uploadFile(String xpath, String filePath) {
		logger.info("File upload arguments received - Xpath : " +xpath+ ", file path : " + filePath+ ".");
		WebElement upload = driver.findElement(By.xpath(xpath));
		upload.click();
		logger.info("Clicked xpath " +xpath);
		
		threadSleep(2);

		File file = new File(filePath);
		String path = file.getAbsolutePath();

		StringSelection selection = new StringSelection(path);
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		logger.info("Keyboard functions passed.");

		Actions actions = new Actions(driver);
		actions.moveToElement(upload).sendKeys(Keys.CONTROL, "v").build().perform();
		logger.info("File uploaded.");
	}

	public void acceptMendixAlert()
	{
		threadSleep(1);
		clickByXpath("//button[@class='btn btn-primary']");
		logger.info("Accepted mendix alert");
	}
	
	/**
	 * This method will accept the alert
	 * 
	 * @author Deshik - MX Techies
	 */
	public void acceptAlert() {

		driver.switchTo().alert().accept();
	}

	/**
	 * This method will get the text from alert and return the same
	 * 
	 * @return
	 * @author Deshik - MX Techies
	 */
	public String getAlertText() {
		return driver.switchTo().alert().getText();

	}

	/**
	 * This method will dismiss the alert
	 */
	public void dismissAlert() {

		driver.switchTo().alert().dismiss();
	}

	/**
	 * This method will switch back to main window from an alert
	 */
	public void switchToMainWindow() {
		driver.switchTo().defaultContent();
	}

}

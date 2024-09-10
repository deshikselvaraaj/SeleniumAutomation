package com.test.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Sample {
	
	public static void main(String[] args) {
		System.out.println("Started");
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.google.com");
		System.out.println("Ended");
	}

}

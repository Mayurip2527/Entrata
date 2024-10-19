package com.webPages;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.aventstack.extentreports.ExtentTest;
import com.basePackage.TestBase;

public class WatchDemoPage extends TestBase{
	
	public WebDriver wdriver;
	private ExtentTest test;
	private String strscreenshotFolderpath;
	public String strScreenshot;
	public Object[][] arrGetdata;
	
	public WatchDemoPage(WebDriver wdriver, ExtentTest test, String screenshotFolderpath, Properties propfile) {
		this.driver = wdriver;
		this.test = test;
		this.strscreenshotFolderpath = screenshotFolderpath;
		this.prop = propfile;
		PageFactory.initElements(wdriver, this);
	}
	
	@FindBy(id = "FirstName")
	public WebElement firstName;
	
	@FindBy(id = "LastName")
	public WebElement lastName;
	
	@FindBy(id = "Email")
	public WebElement email;
	
	@FindBy(id = "Company")
	public WebElement company;
	
	@FindBy(id = "Phone")
	public WebElement phone;
		
	@FindBy(id = "Unit_Count__c")
	public WebElement unitCount;
	
	@FindBy(id = "Title")
	public WebElement title;
	
	@FindBy(id = "demoRequest")
	public WebElement demoRequest;
	
	@FindBy(xpath = "//button[@type='submit']")
	public WebElement submit;
	
	
	public void verifyWatchdemo(String fname, String lName, String emailid, String companyName,String phoneNumber, String jobTitle) throws InterruptedException, HeadlessException, IOException, AWTException {
		
		HomePage homePage = new HomePage(driver, test, strscreenshotFolderpath, prop);		
		click(homePage.watchDemoButton.get(0));
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Click on Watch Demo button from Home Page");
		setText(firstName, fname);
		setText(lastName, lName);
		setText(email, emailid);
		setText(company, companyName);
		setText(phone, phoneNumber);
		selectDropDown(unitCount, "1 - 10");	
		setText(title, jobTitle);
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Input Provided to All text fields");	
		Assert.assertTrue(isDisplayed(submit), "The Watch Demo button is not displayed on Watch Demo page");
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Watch Demo button is button is present on Watch Demo Page");
		
	}
	

}

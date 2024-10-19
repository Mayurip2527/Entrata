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

public class ResidentPayPage extends TestBase{
	
	
	HomePage homePage;
	public WebDriver wdriver;
	private ExtentTest test;
	private String strscreenshotFolderpath;
	public String strScreenshot;
	public Object[][] arrGetdata;
	
	public ResidentPayPage(WebDriver wdriver, ExtentTest test, String screenshotFolderpath, Properties propfile) {
		this.driver = wdriver;
		this.test = test;
		this.strscreenshotFolderpath = screenshotFolderpath;
		this.prop = propfile;
		PageFactory.initElements(wdriver, this);
	}
	
	@FindBy(xpath = "//h1[contains(text(),'ResidentPay')]")
	public WebElement residentPayText;
	
	
	
	public void verifyResidentPage() throws InterruptedException, HeadlessException, IOException, AWTException {
		 homePage = new HomePage(driver, test, strscreenshotFolderpath, prop);
		 click(homePage.closeButton);
		 mouseHover(homePage.productButton);
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Mouse Hover on Product");
		 click(homePage.residentButton);
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Clicked on Resident Pay");
		 Assert.assertTrue(isDisplayed(residentPayText), "Resident pay text is not displayed when redirected from home page");		 
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Resident Text is Present on Resident pay page");
	}
	 
	
	

}


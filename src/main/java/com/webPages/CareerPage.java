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

public class CareerPage extends TestBase{
	
	HomePage homePage;
	public WebDriver wdriver;
	private ExtentTest test;
	private String strscreenshotFolderpath;
	public String strScreenshot;
	public Object[][] arrGetdata;
	
	public CareerPage(WebDriver wdriver, ExtentTest test, String screenshotFolderpath, Properties propfile) {
		this.driver = wdriver;
		this.test = test;
		this.strscreenshotFolderpath = screenshotFolderpath;
		this.prop = propfile;
		PageFactory.initElements(wdriver, this);
	}
	
	@FindBy(xpath = "//div[contains(text(),'Search Jobs')]")
	public List<WebElement> searchJob;
	
	
	
	public void verifyCareerPage() throws InterruptedException, AWTException, HeadlessException, IOException {
		
		 homePage = new HomePage(driver, test, strscreenshotFolderpath, prop);
		 click(homePage.acceptCookies);
		 scrollElementusingJavaScriptExecutor(driver, homePage.career);
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Scrolled down to the footer of the page to click on Career");
		 click(homePage.career);
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Clicked on carrer option");
		 click(searchJob.get(0));
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Clicked on Search Jobs");
		 handleWindows(1);
		 Assert.assertEquals(driver.getTitle(), "Entrata", "Title of the job page is not displayed");
		 takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Title of the job is displayed");
			
	}
	 
	
	

}


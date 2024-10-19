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

public class SingInPage extends TestBase{
	
	public WebDriver wdriver;
	private ExtentTest test;
	private String strscreenshotFolderpath;
	public String strScreenshot;
	public Object[][] arrGetdata;
	
	public String header = "Sign in";
	
	public SingInPage(WebDriver wdriver, ExtentTest test, String screenshotFolderpath, Properties propfile) {
		this.driver = wdriver;
		this.test = test;
		this.strscreenshotFolderpath = screenshotFolderpath;
		this.prop = propfile;
		PageFactory.initElements(wdriver, this);
	}
	

	
	@FindBy(id = "entrata-username")
	public WebElement userName;
	
	@FindBy(id = "entrata-password")
	public WebElement password;
	
	@FindBy(xpath = "//button[contains(text(),'Sign In')]")
	public WebElement singInButton;
	
	@FindBy(xpath = "//div[contains(text(),'Property Manager Login')]")
	public WebElement properyManagerLogin;
	
	
	@FindBy(xpath = "//span[contains(text(),'Sign in')]")
	public WebElement singInPageHeader;
	
	

	
	public void verifySignIn(String un, String pwd) throws InterruptedException, HeadlessException, IOException, AWTException {
		
		HomePage homePage = new HomePage(driver, test, strscreenshotFolderpath, prop);		
		click(homePage.singButton.get(0));
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Click on Sing In button from Home Page");		
		click(homePage.acceptCookies);
		click(properyManagerLogin);
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Click on Property Manager Login option");
		Assert.assertEquals(getText(singInPageHeader), header, "The user is not redirected to Sing page after clicking on Property Manager Login option" );
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "The user is redirected to Sing in Page");		
		setText(userName,un);
		setText(password, pwd);
		
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Username and Password are provided");	
		Assert.assertTrue(isDisplayed(singInButton), "The Sing In button is not displayed on Sing In page");
		takeScreenshotsLogsExtentReportatTestStep(driver, strscreenshotFolderpath, "Sing In button is present on Sing In Page");
		
	}
	
	

}


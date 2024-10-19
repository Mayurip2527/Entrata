package com.webPages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.aventstack.extentreports.ExtentTest;
import com.basePackage.TestBase;

public class HomePage extends TestBase{
	
	public WebDriver wdriver;
	private ExtentTest test;
	private String strscreenshotFolderpath;
	public String strScreenshot;
	public Object[][] arrGetdata;
	
	public HomePage(WebDriver wdriver, ExtentTest test, String screenshotFolderpath, Properties propfile) {
		this.driver = wdriver;
		this.test = test;
		this.strscreenshotFolderpath = screenshotFolderpath;
		this.prop = propfile;
		PageFactory.initElements(wdriver, this);
	}
	
	@FindBy(xpath = "//a[contains(text(),'Sign In')]")
	public List<WebElement> singButton;
	
	@FindBy(xpath = "//div[contains(text(),'Watch Demo')]")
	public List<WebElement> watchDemoButton;
	
	@FindBy(xpath = "//div[@class='footer-nav']//a[contains(text(),'ResidentPay')]")
	public WebElement residentButton;
	
	@FindBy(xpath = "//a[contains(text(),'Guides')]")
	public WebElement guideButton;
	
	@FindBy(xpath = "//div[contains(text(),'Products')]")
	public WebElement productButton;
	
	@FindBy(xpath = "//button[@aria-label='close']")
	public WebElement closeButton;
	
	
	@FindBy(xpath = "//div[contains(text(),'Resources')]")
	public WebElement resourceButton;
	
	@FindBy(xpath = "//a[contains(text(),'Accept Cookies')]")
	public WebElement acceptCookies;
	
	@FindBy(xpath = "//a[contains(text(),'Careers')]")
	public WebElement career;
	
	 
	
	

}


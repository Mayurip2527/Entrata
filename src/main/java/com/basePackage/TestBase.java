package com.basePackage;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.aventstack.extentreports.Status;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.bson.Document;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;

import com.Utilities.Screenshot;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	// ============================================================GLOBAL
	// VARIABLES======================================================================
	public WebDriver driver;
	public Properties prop;
	public static ExtentHtmlReporter reporter;
	public static ExtentReports extent;
	public static ExtentTest test;

	public static Capabilities cap;
	public String Folderpath;
	public static File ParentFolderPath;
	public FileInputStream configurationfile;
	private String strscreenshotFolderpath;
	public String filePath = System.getProperty("user.dir") + "//Files" + "//";
	
	public Logger log = Logger.getLogger("devpinoyLogger");

	public void setup() throws InterruptedException {
		try {
			PropertyConfigurator.configure(".//src//main//java//com//configUtility//log4j.properties");
			configurationfile = new FileInputStream(".//src//main//java//com//configUtility//Configuration.properties");
			prop = new Properties();
			try {
				prop.load(configurationfile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Configuration File not Found");
		}

		if (prop.getProperty("BROWSER").equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			log.info("Chrome Browser initiated");

		} else if (prop.getProperty("BROWSER").equalsIgnoreCase("IE")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		}
		 else if (prop.getProperty("BROWSER").equalsIgnoreCase("Firefox")) {
			 WebDriverManager.firefoxdriver().setup();
			driver = new InternetExplorerDriver();
		}

		else if (prop.getProperty("BROWSER").equalsIgnoreCase("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();


		}

		driver.get(prop.getProperty("URL"));
		log.info("URL invoked :" + prop.getProperty("URL"));
		Thread.sleep(1000); // For maximize the browser
		// Maximize the Browser
		driver.manage().window().maximize();
   
		log.info("Browser Maximized :" + prop.getProperty("URL"));
	}
	// ==================================================================================================================================


	// ==============SELECT DROP DOWN METHOD============================
	public WebElement selectDropDown(WebElement element, String selectdropdownoption) {
		waitForElementToBeVisible(element);
		Select dropdown = new Select(element);
		dropdown.selectByVisibleText(selectdropdownoption);
		return element;
	}


	public String getText(WebElement element) throws InterruptedException {
		Thread.sleep(1000); // for get text of element
		waitForElementToBeVisible(element);
		if (isDisplayed(element) == true) {
			return element.getText();
		} else {
			String t = "No Text Found";
			return t;
		}
	}

	// ============ENTER TEXT INTO TEXT FIELD METHOD=====================
	public void setText(WebElement element, String inputvalue) throws InterruptedException {
		Thread.sleep(500); // wait for element
		waitForElementToBeVisible(element);
		element.sendKeys(inputvalue);
	}

	// ===========CLICK METHOD TO CLICK ON BUTTON=========================
	public void click(WebElement element) throws InterruptedException {
		try {
			Thread.sleep(1000); // wait for element
			waitForElementToBeClickable(element);
			element.click();
			Thread.sleep(1000);
		} catch (Exception e) {
			Thread.sleep(500); // wait for element
			waitForElementToBeVisible(element);
			Actions actions = new Actions(driver);
			actions.moveToElement(element).click().perform();
			Thread.sleep(500);
		}
	}
	
	// ===========CLICK METHOD TO CLICK ON BUTTON=========================
	public void click(List<WebElement> element, int i) throws InterruptedException {
		try {
			Thread.sleep(1000); // wait for element
			waitForElementToBeClickable(element.get(i));
			element.get(i).click();
			Thread.sleep(1000);
		} catch (Exception e) {
			Thread.sleep(500); // wait for element
			waitForElementToBeVisible(element.get(i));
			Actions actions = new Actions(driver);
			actions.moveToElement(element.get(i)).click().perform();
			Thread.sleep(500);
		}
	}

	// ===========CHECK ELEMENT IS DISPLAYED OR NOT=============================
	public boolean isDisplayed(WebElement element) throws InterruptedException {
		try {
			Thread.sleep(500); // wait till the element is visible
			return element.isDisplayed();
		} catch (NoSuchElementException ignored) {
			return false;
		}
	}


	public void mouseHover(WebElement caret, WebElement subcaret) throws InterruptedException // Mouse Hover on
																								// WebElements
	{
		// Below threads are required for mouse hover to element
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		waitForElementToBeVisible(caret);
		action.moveToElement(caret).click().build().perform();
		waitForElementToBeVisible(subcaret);
		Thread.sleep(500);
		click(subcaret);
	}

	public void waitForElementToBeVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// ===========COMMON METHOD FOR WAIT TILL UI ELEMENT IS VISIBLE===========
	public void waitForElementToVisible(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	// ===========COMMON METHOD FOR WAIT TILL UI PRESENCE OF ELEMENT===========
	public void waitForPresenceOfElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfElementLocated(element));
	}

	// ===========COMMON METHOD FOR WAIT UNTILL ALL UI PRESENCE OF
	// ELEMENT===========
	public void waitForPresenceOfAllElement(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(element));
	}

	// ===========WAIT TILL UI ELEMENT IS CLICKABLE===========================
	public void waitForElementToBeClickable(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	// ===========COMMON METHOD FOR WAIT TILL UI ELEMENT IS VISIBLE===========
	public void waitForElementToBeVisible(WebElement element, long time) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public void waitForElemementToAppear(WebElement element) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10))
				.pollingEvery(Duration.ofSeconds(1)).ignoring(NoSuchElementException.class);
		wait.until(ExpectedConditions.visibilityOf(element));
	}


	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}


	public void takeScreenshotsLogsExtentReportatTestStep(WebDriver wdriver, String Screenshotpath, String text)
			throws IOException, InterruptedException, HeadlessException, AWTException {
		this.driver = wdriver;
		log.info(text);
		test.log(Status.INFO, text);
		Screenshot.takeScreenShot(driver, Screenshotpath, text);
	}

	public void clickElementusingJavaScriptExecutor(WebDriver wdriver, WebElement element) throws InterruptedException {
		this.driver = wdriver;
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
		Thread.sleep(2000);
	}

	public void scrollElementusingJavaScriptExecutor(WebDriver wdriver, WebElement element)
			throws InterruptedException, AWTException {
		try {
			this.driver = wdriver;
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			Thread.sleep(1000); // For initialize the java script variable
			executor.executeScript("arguments[0].scrollIntoView();", element);
		} catch (Exception e) {
			Robot rob = new Robot();
			rob.keyPress(KeyEvent.VK_PAGE_DOWN);
			rob.keyPress(KeyEvent.VK_PAGE_DOWN);
			rob.keyPress(KeyEvent.VK_PAGE_DOWN);
			rob.keyRelease(KeyEvent.VK_PAGE_DOWN);
			Thread.sleep(1000);
		}
	}

	public void extentReportInitialize(String ReportName) {
		// Create Report with Name Automation_Report with Current Date and Time
		reporter = new ExtentHtmlReporter(".//Reports//"+ReportName+"_" + getDate() + ".html");
		reporter.setAppendExisting(true);
		// Set Document Title as "Automation Test Reports"
		reporter.config().setDocumentTitle("Automation Test Reports");
		// Set Report Name as "Reports"
		reporter.config().setReportName("Reports");
		reporter.setAppendExisting(true);
		extent = new ExtentReports();
		extent.setReportUsesManualConfiguration(true);
		extent.attachReporter(reporter);
		// Get OS Name
		extent.setSystemInfo("OS Name", System.getProperty("os.name"));
		// Get OS Version
		extent.setSystemInfo("OS Version", System.getProperty("os.version"));
		// Get OS Version
		extent.setSystemInfo("User Information", System.getProperty("user.name"));

	}

	@BeforeSuite
	public void screenshots() {
		// Create Folder in Screenshots Folder with Current Date and Time
		Folderpath = System.getProperty("user.dir") + "//Screenshots" + "//" +getDate();
		ParentFolderPath = new File(Folderpath);
		ParentFolderPath.mkdir();
		
		
	}
	
	@AfterSuite
	public void closeBrowserandextendReport() throws IOException {
		extentReportCloser();
		
	}
	public void extentReportCloser() {
		cap = ((RemoteWebDriver) driver).getCapabilities();
		// Get Browser Name
		extent.setSystemInfo("Browser Name", cap.getBrowserName());
		// Get Browser Version
		extent.setSystemInfo("Browser Version", cap.getBrowserVersion());
		// Get URL
		extent.setSystemInfo("Estimate Web Interface URL", prop.getProperty("URL"));
		extent.flush();
	}



	public void handleWindows(int i) {
		ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
		// Switch to the new tab
		driver.switchTo().window(tabs.get(i));

	}

	
	public void mouseHover(WebElement ele) throws InterruptedException // Mouse Hover on
	{
	
		Actions action = new Actions(driver);
		Thread.sleep(1000);
		waitForElementToBeVisible(ele);
		action.moveToElement(ele).click().build().perform();

	}

}

package com.testCases;

import java.awt.AWTException;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import com.basePackage.TestBase;
import com.webPages.WatchDemoPage;
import com.Utilities.ExcelFile;
import com.Utilities.ExtentReportUtilities;


public class WatchDemoTest extends TestBase {

	WatchDemoPage watchDemoPage;
	
	private String strTestCaseId;
	private String strTestCaseDescription;
	private String strscreenshotWatchDemo;
	private static File fileobjWatchDemoPath;
	private static String strSheetPath = ".//TestData//TestCase.xlsx";

	@BeforeClass
	public void prerequisites() {
		extentReportInitialize("WatchDemoTest");
		fileobjWatchDemoPath = new File(ParentFolderPath + "//" + "WatchDemoTest");
		fileobjWatchDemoPath.mkdir();

	}

	// Launch the browser and login in to the ECM Web
	@BeforeMethod
	public void initialise() throws Exception {
		setup();
	}
	// Reading Data from excel from 1st sheet

	@DataProvider
	public Object[][] watchDemo() throws IOException {
		System.out.println(" watchDemo ");
		Object[][] arrGetdata = ExcelFile.readDataFromExcel(strSheetPath, 1);
		return arrGetdata;
	}

	// This Test case Covers the Partially Approved and Approved status from Email
	 @Test(priority = 1, dataProvider = "watchDemo")
	public void verifyWatchDemoTest(String strtestcasename, String
	strdescription, String fname, String lName, String emailid, String companyName,String phoneNumber, String jobTitle)
			throws InterruptedException, IOException, HeadlessException, AWTException {

		testcaseStarted(strtestcasename, strdescription);
		watchDemoPage = new WatchDemoPage(driver, test, strscreenshotWatchDemo, prop);
		watchDemoPage.verifyWatchdemo(fname, lName,
				emailid, companyName, phoneNumber, jobTitle);
		
	}

	

	// Capture Test Case Pass Fail Result
	@AfterMethod
	public void getResult(ITestResult result) throws IOException, InterruptedException {
		ExtentReportUtilities util = new ExtentReportUtilities();
		util.getResult(result, strSheetPath, strTestCaseDescription, test, reporter, log);
		driver.quit();
	}

	// Prerequisites Before Start TestCase(eg: Create test in extent report, create
	// test case ID folder for screenshot & Logs for test Case is started
	public void testcaseStarted(String testCaseID, String testCaseDescription) {
		strTestCaseId = testCaseID;
		strTestCaseDescription = testCaseDescription;
		test = extent.createTest(strTestCaseId + "_" + testCaseDescription);
		strscreenshotWatchDemo = fileobjWatchDemoPath + "//" + strTestCaseId;
		log.info("===========Test Case Started: " + strTestCaseId + "_" + strTestCaseDescription + "===========");
	}


	@AfterClass
	public void closeBrowserandextendReport() throws IOException {
		extentReportCloser();
		
	}

}

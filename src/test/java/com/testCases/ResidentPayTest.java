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
import com.webPages.ResidentPayPage;
import com.webPages.SingInPage;
import com.webPages.WatchDemoPage;
import com.Utilities.ExcelFile;
import com.Utilities.ExtentReportUtilities;


public class ResidentPayTest extends TestBase {

	ResidentPayPage residentPayPage;
	
	private String strTestCaseId;
	private String strTestCaseDescription;
	private String strscreenshotSingIn;
	private static File fileobjResidentPayPath;
	private static String strSheetPath = ".//TestData//TestCase.xlsx";
	
	@BeforeClass
	public void prerequisites() {
		extentReportInitialize("ResidentPayTest");
		fileobjResidentPayPath = new File(ParentFolderPath + "//" + "ResidentPayTest");
		fileobjResidentPayPath.mkdir();

	}

	// Launch the browser and login in to the ECM Web
	@BeforeMethod
	public void initialise() throws Exception {
		setup();
	}

	
	@DataProvider
	public Object[][] ResidentPayData() throws IOException {
		System.out.println(" ResidentPayTest ");
		Object[][] arrGetdata = ExcelFile.readDataFromExcel(strSheetPath, 3);
		return arrGetdata;
	}
	
	 @Test(priority = 3, dataProvider ="ResidentPayData")
	public void verifyResidentTest(String strtestcasename, String
			strdescription)
			throws InterruptedException, IOException, HeadlessException, AWTException {

		 testcaseStarted(strtestcasename, strdescription);		 
		 residentPayPage = new ResidentPayPage(driver, test, strscreenshotSingIn, prop);
		 residentPayPage.verifyResidentPage();
		

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
		strscreenshotSingIn = fileobjResidentPayPath + "//" + strTestCaseId;
		log.info("===========Test Case Started: " + strTestCaseId + "_" + strTestCaseDescription + "===========");
	}

	@AfterClass
	public void closeBrowserandextendReport() throws IOException {
		extentReportCloser();
		
	}

}

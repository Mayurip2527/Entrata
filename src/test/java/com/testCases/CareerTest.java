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
import com.webPages.CareerPage;
import com.webPages.ResidentPayPage;
import com.webPages.SingInPage;
import com.webPages.WatchDemoPage;
import com.Utilities.ExcelFile;
import com.Utilities.ExtentReportUtilities;


public class CareerTest extends TestBase {

	CareerPage careerPage;
	
	private String strTestCaseId;
	private String strTestCaseDescription;
	private String strscreenshotSingIn;
	private static File fileobjSingInPath;
	private static String strSheetPath = ".//TestData//TestCase.xlsx";
	
	@BeforeClass
	public void prerequisites() {
		extentReportInitialize("CareerTest");
		fileobjSingInPath = new File(ParentFolderPath + "//" + "CareerTest");
		fileobjSingInPath.mkdir();

	}

	// Launch the browser and login in to the ECM Web
	@BeforeMethod
	public void initialise() throws Exception {
		setup();
	}

	
	@DataProvider
	public Object[][] careerData() throws IOException {
		System.out.println(" careerData ");
		Object[][] arrGetdata = ExcelFile.readDataFromExcel(strSheetPath, 4);
		return arrGetdata;
	}
	
	 @Test(priority = 4, dataProvider ="careerData")
	public void verifyCareerTest(String strtestcasename, String
			strdescription)
			throws InterruptedException, IOException, HeadlessException, AWTException {

		 testcaseStarted(strtestcasename, strdescription);		 
		 careerPage = new CareerPage(driver, test, strscreenshotSingIn, prop);
		 careerPage.verifyCareerPage();
		

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
		strscreenshotSingIn = fileobjSingInPath + "//" + strTestCaseId;
		log.info("===========Test Case Started: " + strTestCaseId + "_" + strTestCaseDescription + "===========");
	}

	@AfterClass
	public void closeBrowserandextendReport() throws IOException {
		extentReportCloser();
		
	}

}

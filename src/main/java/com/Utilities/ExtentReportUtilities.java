package com.Utilities;

import org.testng.ITestResult;

import java.io.IOException;

import org.apache.log4j.Logger;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentReportUtilities {
	// =============================================COMMON METHOD FOR WRITTING
	// PASS/FAIL/SKIP RESULT===============================================
	public void getResult(ITestResult result, String SheetName, String TestDescription, ExtentTest test,
			ExtentHtmlReporter extent, Logger log) throws IOException, InterruptedException {
		if (result.getStatus() == ITestResult.FAILURE) {
			// Write Test case Fail Status in Login Excel File
			ExcelFile.writeDataToExcelFile(SheetName, 0, TestDescription, "FAIL");
			// Write Test case Fail Status in Extent report
			test.log(Status.FAIL, MarkupHelper.createLabel(" FAILED ", ExtentColor.RED));
			test.fail(result.getThrowable());
			log.info("=========================TEST CASE IS FAILED ===================");
			// Close extent report
			extent.flush();
			Thread.sleep(1000);
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			// Write Test case Pass Status in Login Excel File
			ExcelFile.writeDataToExcelFile(SheetName, 0, TestDescription, "PASS");
			test.log(Status.PASS, MarkupHelper.createLabel(" PASSED ", ExtentColor.GREEN));
			// Write Test case Pass Status in Extent report
			log.info("=========================TEST CASE IS PASSED ===================");
			// Close extent report
			extent.flush();
			Thread.sleep(1000);
		} else {
			// Write Test case Skip Status in Login Excel File
			ExcelFile.writeDataToExcelFile(SheetName, 0, TestDescription, "SKIP");
			// Write Test case Skip Status in Extent report
			test.log(Status.SKIP, MarkupHelper.createLabel(" SKIPPED ", ExtentColor.ORANGE));
			test.skip(result.getThrowable());
			log.info("==========================TEST CASE IS SKIPPED ===================");
			// Close extent report
			extent.flush();
			Thread.sleep(1000);
		}
	}

}

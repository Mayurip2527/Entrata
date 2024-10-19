package com.Utilities;


import java.awt.AWTException;
import java.awt.HeadlessException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import javax.imageio.ImageIO;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.basePackage.TestBase;

public class Screenshot {
	
		//=======================================COMMON METHOD FOR CATURING/TAKING SCREENSHOT OF WEBPAGE====================================
	public static void takeScreenShot(WebDriver webDriver, String screenshotFolderpath, String imageName)
			throws IOException, InterruptedException, HeadlessException, AWTException {
		String date = getDate();
		File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
		if (TestBase.ParentFolderPath.exists()) {
			// Create folder in Screenshot path with Test Case ID
			File DestinationPath = new File(screenshotFolderpath);
			if (DestinationPath.exists()) {
				// Capture Screenshots and paste it into Test Case ID
				File destFile = new File(DestinationPath + "//" + imageName + "_" + date + ".png");
				FileUtils.copyFile(scrFile, destFile);
			} else {
				DestinationPath.mkdirs();
				Thread.sleep(1000);
				File destFile = new File(DestinationPath + "//" + imageName + "_" + date + ".png");
				FileUtils.copyFile(scrFile, destFile);
			}
		}
	}

	// =======================================COMMON METHOD FOR CATURING/TAKING
	// SCREENSHOT OF FULL PAGE OF WINDOW==========================
	public static void takeFullWindowScreenShot(WebDriver webDriver, String screenshotFolderpath, String imageName)
			throws IOException, InterruptedException, HeadlessException, AWTException {
		String date = getDate();
		BufferedImage image = new Robot()
				.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		if (TestBase.ParentFolderPath.exists()) {
			// Create folder in Screenshot path with Test Case ID
			File DestinationPath = new File(screenshotFolderpath);
			if (DestinationPath.exists()) {
				// Capture Screenshots and paste it into Test Case ID
				File destFile = new File(DestinationPath + "//" + imageName + "_" + date + ".png");
				ImageIO.write(image, "png", destFile);
			} else {
				DestinationPath.mkdirs();
				Thread.sleep(1000);
				File destFile = new File(DestinationPath + "//" + imageName + "_" + date + ".png");
				ImageIO.write(image, "png", destFile);
			}

		}

	}

	// ==================================================COMMON METHOD FOR TO GET
	// SYSTEM CURRENT DATE AND TIME===========================================
	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
		Calendar cal = Calendar.getInstance();
		return dateFormat.format(cal.getTime());
	}
	// ==================================================================================================================================================

}

package com.Utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

public class Cleanup {

	public static void main(String[] args) throws IOException {
		// =====================Deletes content of reports,Screenshot and logs
		// Folders=========================
		File ScreenshotsDirectory = new File(System.getProperty("user.dir") + "\\Screenshots");
		File Reports = new File(System.getProperty("user.dir") + "\\Reports");
		File Logs = new File(System.getProperty("user.dir") + "\\Logs");
		try {
			FileUtils.cleanDirectory(ScreenshotsDirectory);
			FileUtils.cleanDirectory(Reports);
			FileUtils.cleanDirectory(Logs);

		} catch (IOException e) {
			e.printStackTrace();
		}
		// =============================Clearing status From result
		// excel=========================================================
		ExcelFile.FillblankstatusinStatusColoumn(".//TestData//TestCase.xlsx", 0);

		System.out.println("Clean up done successfully");
	}

}

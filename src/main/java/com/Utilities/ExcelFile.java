package com.Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFile {

	public static int rowcount;
	public static int coloumcount;
	private static XSSFWorkbook wb;

	// ==================================COMMON METHOD FOR READ DATA FROM EXCEL
	// FILE==============================
	public static Object[][] readDataFromExcel(String ExcelFile, int Sheetindex) throws IOException {
		Object[][] ExcelData;
		FileInputStream File = new FileInputStream(ExcelFile);
		wb = new XSSFWorkbook(File);
		XSSFSheet sheet = wb.getSheetAt(Sheetindex);
		rowcount = sheet.getLastRowNum();
		coloumcount = sheet.getRow(0).getLastCellNum();
		/*
		 * System.out.println("row=> " +rowcount); System.out.println("coloumcount=> "
		 * +coloumcount);
		 */
		ExcelData = new Object[rowcount][coloumcount];
		for (int i = 1, ci = 0; i <= rowcount; i++, ci++) {
			for (int j = 0, cj = 0; j < coloumcount; j++, cj++) {
				// Get cell value
				ExcelData[ci][cj] = getCellValue(sheet.getRow(i).getCell(j));
				System.out.println(ExcelData[ci][cj]);
			}
		}
		return ExcelData;
	}

	// ================COMMON METHOD FOR GET CELL VALUE FROM EXCEL SHEET(NUMERIC
	// VALUE,STRING VALUE)===========
	public static String getCellValue(XSSFCell cell) {
		String CellValue = null;
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case NUMERIC:
				CellValue = String.valueOf((cell.getNumericCellValue()));
				break;

			case STRING:
				CellValue = cell.getStringCellValue();
				break;

			default:
				break;
			}
			return CellValue;
		} else {
			return CellValue = "";
		}
	}

	// =====================COMMON METHOD FOR WRITE DATA(PASS/FAIL RESULT OF
	// TESTCASE) INTO EXCEL FILE==============================
	public static void writeDataToExcelFile(String ExcelFile, int Sheetindex, String testCaseDescription, String status)
			throws IOException

	{
		FileInputStream fis = new FileInputStream(ExcelFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(Sheetindex);
		int TotalRow = sheet.getLastRowNum();

		for (int i = 1; i <= TotalRow; i++) {
			if (sheet.getRow(i).getCell(1).getStringCellValue().equalsIgnoreCase(testCaseDescription)) {
				if (status.equalsIgnoreCase("PASS")) {
					sheet.getRow(i).createCell(2).setCellValue(status);
				} else {
					sheet.getRow(i).createCell(2).setCellValue(status);
				}
				FileOutputStream fout = new FileOutputStream(ExcelFile);
				wb.write(fout);
				fout.close();
			}

		}
		wb.close();
	}

	// ============================================================================================================================
	public static void FillblankstatusinStatusColoumn(String ExcelFile, int Sheetindex) throws IOException {
		FileInputStream fis = new FileInputStream(ExcelFile);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(Sheetindex);
		int TotalRow = sheet.getLastRowNum();

		for (int i = 1; i <= TotalRow; i++) {
			sheet.getRow(i).createCell(2).setCellValue(" "); // Set Cell Value as PASS
			FileOutputStream fout = new FileOutputStream(ExcelFile);
			wb.write(fout);
			fout.close();
		}
		wb.close(); // Close Workbook
	}

	// ============================================================================================================================
	public static Object[][] readDataExcel(String ExcelFile, int Sheetindex) throws IOException {
		Object[][] ExcelData;
		FileInputStream File = new FileInputStream(ExcelFile);
		wb = new XSSFWorkbook(File);
		XSSFSheet sheet = wb.getSheetAt(Sheetindex);
		rowcount = sheet.getLastRowNum();
		coloumcount = sheet.getRow(rowcount).getLastCellNum();
		ExcelData = new Object[rowcount + 1][coloumcount];
		for (int i = 0, ci = 0; i <= rowcount; i++, ci++) {
			for (int j = 0, cj = 0; j < coloumcount; j++, cj++) {
				// Get cell value
				try {
					ExcelData[ci][cj] = getCellValue(sheet.getRow(i).getCell(j));
					System.out.println(ExcelData[ci][cj]);
				} catch (NullPointerException e) {
					System.out.println(e);
				}

			}
		}
		return ExcelData;
	}

}

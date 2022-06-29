package com.domain.utilityClass;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.io.FileHandler;

public class Utility {
	
	static Sheet sh;
	/**
	 * This method is for capture the screenshots once any method get failed
	 * 
	 * @param driver
	 * @param testName -- name of failed method
	 */

	public static void captureScreenshot(WebDriver driver, String testName) throws IOException {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Screenshots/" + testName + ".png");
			FileHandler.copy(scrFile, destFile);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * This method is for accessing external data from excel file
	 * 
	 * @param rowIndex   -- provide the row index by seeing the excel sheet for
	 *                   particular data
	 * @param colIndex-- provide the row index by seeing the excel sheet for
	 *                   particular data
	 */

	public static String getTestData(int rowIndex, int colIndex) throws EncryptedDocumentException, IOException {
		FileInputStream file = new FileInputStream("./src/test/resources/testData/testdata.xlsx");
		try {
			sh = WorkbookFactory.create(file).getSheet("TestData");
		} catch (EncryptedDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String value = sh.getRow(rowIndex).getCell(colIndex).getStringCellValue();
		return value;
	}

}

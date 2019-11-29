package com.atmecs.assessmenttask.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.atmecs.assessmenttask.constants.FilePath;

/**
 * This class is used to read the excel file's particular cell data and get the
 * row count and column count of the excel file
 */



public class ExcelFileReader {

	XSSFWorkbook workbook;
	XSSFSheet sheet;
	File file;
	String path;
     Properties testdata;
	/**
	 * This constructor will get the filepath of the testdata file and create the
	 * Xssfworkbook object
	 */
	public ExcelFileReader(String filePath) throws IOException {

		File file = new File(filePath);
		FileInputStream fileInput = new FileInputStream(file);
		workbook = new XSSFWorkbook(fileInput);
		testdata= new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}

	/**
	 * This method will return the value of the particular cell from the excel file
	 */
	public String gettingExcelFileCellValue(String sheetName, int rowNumber, int cellNumber) {

		sheet = workbook.getSheet(sheetName);
		String data = sheet.getRow(rowNumber).getCell(cellNumber).getStringCellValue();
		return data;
	}

	/**
	 * This method will return the total number of rows in an excel file
	 */
	public int countingTheNumberOfRows(String sheetName) {

		int rowCount = workbook.getSheet(sheetName).getLastRowNum();
		return rowCount;
	}

	/**
	 * This method will return the total number of column in an excel file
	 */
	public int countingTheNumberOfColumns(String sheetName, int rowNumber) {

		int columnCount = workbook.getSheet(sheetName).getRow(rowNumber).getLastCellNum();
		return columnCount;
	}

	/**
	 * This method is used by testdata provider to provide each row value to the
	 * maximum column limit
	 */
	public String gettingExcelFileData(int sheetIndex, int rowNumber, int columnNumber) {
        String sheetName=testdata.getProperty("expdata.sheetname");
		int index;
		String[] array = new String[30];
		for (index = rowNumber; index < array.length; index++) {
			array[index - 1] = gettingExcelFileCellValue(sheetName, index, columnNumber);
		}
		return array[index - 1];
	}

	/**
	 * This method is used to get the values of the complete column by using the
	 * sheetname,columnname as an input
	 */
	
	
	//public String 
	
	
	
	public String[] gettingValuesOfAColumn(String sheetName, String columnName) {
		int columnNumber = 0;
		int rowNumber = 0;
		String excelColumnData[] = new String[countingTheNumberOfRows(sheetName)];
		for (int cellIndex = 1; cellIndex < countingTheNumberOfColumns(sheetName, rowNumber); cellIndex++) {
			if (workbook.getSheet(sheetName).getRow(rowNumber).getCell(cellIndex).getStringCellValue().trim()
					.equals(columnName)) {
				columnNumber = cellIndex;
			}

			for (int rowindex = 1; rowindex <= countingTheNumberOfRows(sheetName); rowindex++) {

				excelColumnData[rowindex - 1] = workbook.getSheet(sheetName).getRow(rowindex).getCell(columnNumber)
						.getStringCellValue();
			}
		}
		return excelColumnData;
	}
	
	/**
	 * This method will get the data from Excel File 
	 * by reading it in a two dimensional array and returns the array
	 */
	public String[][] gettingExcelFileData(String sheetName) throws IOException {
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(FilePath.TESTDATA_FILE);
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		XSSFWorkbook book = null;

		book = new XSSFWorkbook(fileInput);

		org.apache.poi.ss.usermodel.Sheet sheet = book.getSheet(sheetName);
		int row1 = sheet.getLastRowNum();
		row1 += 1;
		int col = sheet.getRow(0).getLastCellNum();
		String array[][] = new String[row1][col];
		int count = 0;
		for (Row row : sheet) {
			int count1 = 0;
			for (Cell cell : row) {
				String Data = cell.toString();
				array[count][count1] = Data;
				count1++;
			}
			count++;

		}
		try {
			book.close();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return array;

	}
}

package com.atmecs.assessmenttask.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;

import com.atmecs.assessmenttask.constants.FilePath;

public class ExcelFileWriter {

	Properties testdata;
	XSSFSheet sheet;
	ExcelFileReader excelreader = new ExcelFileReader(FilePath.TESTDATA_FILE);

	public ExcelFileWriter() throws IOException {

		testdata = new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}

	/**
	 * This method is used to write the data in the excel file by providing the
	 * inputdata in array with initialrownumber ,finalrownumber,column number as an
	 * input
	 */
	public void excelFileWriter(WebDriver driver, String exceldatainput[], int columnnumber, int initialrownumber,
			int finalrownumber) throws IOException {

		File file = new File(FilePath.TESTDATA_FILE);
		FileInputStream fileinput = new FileInputStream(file);
		String sheetname = testdata.getProperty("expdata.sheetname");
		XSSFWorkbook workbook = new XSSFWorkbook(fileinput);
		sheet = workbook.getSheet(sheetname);

		for (int rowindex = 1; rowindex < finalrownumber; rowindex++) {

			Row row = sheet.createRow(rowindex);
			Cell cell = row.createCell(columnnumber);
			cell.setCellValue(exceldatainput[rowindex - 1]);

		}
		try {
			FileOutputStream fileout = new FileOutputStream(FilePath.TESTDATA_FILE);
			workbook.write(fileout);
			fileout.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
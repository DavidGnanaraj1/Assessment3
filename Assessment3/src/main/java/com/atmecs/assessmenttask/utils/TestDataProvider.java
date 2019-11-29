package com.atmecs.assessmenttask.utils;

import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.DataProvider;

import com.atmecs.assessmenttask.constants.FilePath;
  
/**
 * This class is used for running the testscript multiple times 
 * for data in each row starting from the rowindex 1 provided in the testdata file
 */
public class TestDataProvider {
	String path;
	ExcelFileReader readexcel;
	Properties properties;
	
    String sheetname=properties.getProperty("expdata.sheetname");
    
    public  TestDataProvider() throws IOException {
    	properties=new PropertiesFileReader().loadingPropertyFile(FilePath.EXPECTEDDATA_FILE);
	}
    
    
	@DataProvider(name = "login")
	public Object[][] providingTestData() throws IOException {
		readexcel = new ExcelFileReader(FilePath.TESTDATA_FILE);
		int sheetIndex = 0;
		int rowNumber = 1;
		int rowCount = readexcel.countingTheNumberOfRows(sheetname);
		int colCount = readexcel.countingTheNumberOfColumns(sheetname, rowNumber);

		Object[][] excelfiledata = new Object[rowCount][colCount];

		for (int rowindex = 0; rowindex < rowCount; rowindex++) {
			for (int columnindex = 0; columnindex < colCount; columnindex++) {

				  excelfiledata[rowindex][columnindex] = readexcel.gettingExcelFileData(sheetIndex, rowindex + 1,
						columnindex);
			}
		}
		return excelfiledata;
	}
}

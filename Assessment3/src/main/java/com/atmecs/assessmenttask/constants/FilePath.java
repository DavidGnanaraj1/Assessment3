package com.atmecs.assessmenttask.constants;

import java.io.File;

public class FilePath {

	public static final String USER_DIR = System.getProperty("user.dir") + File.separator;

	public static final String CHROME_FILE = USER_DIR + "lib" + File.separator + "chromedriver.exe";

	public static final String FIREFOX_FILE = USER_DIR  + "lib" + File.separator + "geckodriver.exe";

	public static final String IE_FILE = USER_DIR + "lib" + File.separator + "IEDriverServer.exe";

	public static final String CONFIG_FILE = USER_DIR + "configurations" + File.separator + "config.properties";

	public static final String LOCATORS_FILE = USER_DIR + "src/test/resources" + File.separator + "objectRepository" + File.separator
			+ "locators.properties";

	public static final String LOG4J_FILE = USER_DIR + "src/test/resources" + File.separator + "log4j" + File.separator
			+ "log4j.properties";

	public static final String TESTDATA_FILE = USER_DIR + "src/test/resources" + File.separator + "testData" + File.separator
			+ "testdata.xlsx";

	public static final String EXPECTEDDATA_FILE = USER_DIR +"src/test/resources"+File.separator+ "testData"
			+ File.separator + "expecteddata.properties";

}

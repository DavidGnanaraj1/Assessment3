package com.atmecs.assessmenttask.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * This class will help to load the property file and return the properties
 * object for the particular properties file and the value can be obtained by
 * getProperty method
 */

public class PropertiesFileReader {

	public File file;
	public FileReader filereader;
	public Properties properties;

	/**
	 * This method will give the required property file path and return the
	 * properties object
	 */
	public Properties loadingPropertyFile(String path) throws IOException {
		properties = new Properties();
		file = new File(path);
		filereader = new FileReader(file);
		properties.load(filereader);
		return properties;
	}

}

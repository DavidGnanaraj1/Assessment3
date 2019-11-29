package com.atmecs.assessmenttask.helpermethods;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHandlingHelpers {
	public String getDateFormat(String dateFormate) {
		DateFormat dateFormat = new SimpleDateFormat(dateFormate);
		Date date = new Date();
		String currentDate = dateFormat.format(date).toString();
		return currentDate;

	}

}

package com.yodist.yourktm.util;

import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang3.StringUtils;

public class CustomDateUtil {

	public static Integer parseTimeToInt(String timeString) {
	    String[] splitted = StringUtils.split(timeString, ':');
	    try {
	    	return Integer.valueOf(splitted[0]) * 60 + Integer.valueOf(splitted[1]);
	    } catch(Exception ex) {
	    	
	    }
		return null;
	}
	
	public static long convertMsToTodayMinutes(Long ms) {
		if (ms == null) ms = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kuala_Lumpur")).getTimeInMillis();
		Calendar c = Calendar.getInstance(TimeZone.getTimeZone("Asia/Kuala_Lumpur"));
		c.set(Calendar.HOUR_OF_DAY, 0);
		c.set(Calendar.MINUTE, 0);
		c.set(Calendar.SECOND, 0);
		c.set(Calendar.MILLISECOND, 0);
		long millis = (ms - c.getTimeInMillis());
		return millis / 60000;
	}
	
}

package com.yodist.yourktm.util;

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
	
}

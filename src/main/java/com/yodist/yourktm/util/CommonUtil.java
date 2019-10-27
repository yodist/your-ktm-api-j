package com.yodist.yourktm.util;

import java.util.Date;

/**
 * 
 * Provide common utility that being used widely in many services
 * @author Yodi Satriani
 * 
 */
public class CommonUtil {
	
	public static String getUsername()  {
		return "system";
	}
	
	public static Date getDateForSave() {
		return new Date();
	}
 
}

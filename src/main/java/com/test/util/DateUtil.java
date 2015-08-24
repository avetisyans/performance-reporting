package com.test.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static boolean compareByYMD(Date date1, Date date2) {
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		
		cal1.setTime(date1);
		cal2.setTime(date2);
		
		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
		    cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) &&
		    cal1.get(Calendar.DAY_OF_MONTH) == cal2.get(Calendar.DAY_OF_MONTH)) {
			
			return true;
		}
		
		return false;
	}
}

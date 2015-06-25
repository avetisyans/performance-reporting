package com.test.util;

import java.util.concurrent.TimeUnit;

public class DurationConverter {
	
	public static String formatToString(long duration) {

		String hmss = String.format("%02d:%02d:%02d.%03d",
				TimeUnit.MILLISECONDS.toHours(duration),
				TimeUnit.MILLISECONDS.toMinutes(duration) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration)),
				TimeUnit.MILLISECONDS.toSeconds(duration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration)),
				TimeUnit.MILLISECONDS.toMillis(duration) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration)));
		
		return hmss;
	}
}

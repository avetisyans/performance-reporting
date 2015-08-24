package com.test.dto;

import java.util.ArrayList;
import java.util.Date;

import com.test.domain.TestResult;
import com.test.domain.TestResult.Result;

public class DurationsByDate {
	
	private Date day;
	
	private Long totalRuns;
	
	private ArrayList<Long> durations = new ArrayList<Long>();
	
	public DurationsByDate(TestResult testResult) {
		this.day = new Date(testResult.getEndTime());
		this.totalRuns = 1L;
		if (testResult.getResult().equals(Result.SUCCESS)) {
			this.durations.add(testResult.getEndTime() - testResult.getStartTime());
		}
	}

	public Date getDay() {
		return day;
	}
	
	public void setDay(Date day) {
		this.day = day;
	}
	
	public Long getTotalRuns() {
		return totalRuns;
	}
	
	public void setTotalRuns(Long totalRuns) {
		this.totalRuns = totalRuns;
	}

	public ArrayList<Long> getDurations() {
		return durations;
	}

	public void setDurations(ArrayList<Long> durations) {
		this.durations = durations;
	}

}

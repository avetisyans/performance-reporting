package com.test.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;


public class TestResultsByDateDTO {

	private String environmentName;
	private String testCaseName;
	private ArrayList<DateStatistics> dateStatistics = new ArrayList<DateStatistics>();
	
	public String getEnvironmentName() {
		return environmentName;
	}
	
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	
	public String getTestCaseName() {
		return testCaseName;
	}
	
	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public ArrayList<DateStatistics> getDateStatistics() {
		return dateStatistics;
	}

	public void setDateStatistics(ArrayList<DateStatistics> dateStatistics) {
		this.dateStatistics = dateStatistics;
	}
	
	public void addDurationsToStatistics(DurationsByDateWrapper durationsByDateWrapper) {
		for (DurationsByDate durationsByDate : durationsByDateWrapper.getDurationsByDate()) {
			ArrayList<Long> durations = durationsByDate.getDurations();
			Long totalRuns = durationsByDate.getTotalRuns();
			Date day = durationsByDate.getDay();
			long passed = durations.size();
			DateStatistics dateStatistics = new DateStatistics();
			dateStatistics.setDate(day);
			dateStatistics.setTotalRuns(totalRuns);
			dateStatistics.setPassed(passed);
			dateStatistics.setFailed(totalRuns - passed);
			if (passed == 0) {
				dateStatistics.setSuccessfulMin(0L);
				dateStatistics.setSuccessfulMax(0L);
				dateStatistics.setSuccessfulAvg(0L);
			} else {
				dateStatistics.setSuccessfulMin(Collections.min(durations));
				dateStatistics.setSuccessfulMax(Collections.max(durations));
				long sum = 0;
				for (Long long1 : durations) {
					sum += long1;
				}
				dateStatistics.setSuccessfulAvg(sum/passed);	
			}

			this.dateStatistics.add(dateStatistics);
		}
	}
	
/*	public TestCaseWithStatisticsDTO(String testSuiteName, String testCaseName, List<Long> durations, long totalRuns) {
		this.testSuiteName = testSuiteName;
		this.name = testCaseName;
		this.totalRuns = totalRuns;
		long sum = 0;
		for (Long long1 : durations) {
			sum += long1;
		}
		
		this.passed = (long) durations.size();
		this.failed = totalRuns - this.passed;
		//long numberOfPassed = durations.size();
		if (this.passed == 0) {
			this.successfulAvg = 0L;
			this.successfulMax = 0L;
			this.successfulMin = 0L;
		} else {
			this.successfulAvg = sum/this.passed;
			this.successfulMax = Collections.max(durations);
			this.successfulMin = Collections.min(durations);
		}
	}*/
	
}

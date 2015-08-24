package com.test.dto;

import java.util.ArrayList;
import java.util.Date;

import com.test.domain.TestResult;
import com.test.domain.TestResult.Result;
import com.test.util.DateUtil;

public class DurationsByDateWrapper {
	private ArrayList<DurationsByDate> durationsByDate = new ArrayList<DurationsByDate>();
	
	public void addDurationsByDate(TestResult testResult) {
		for (DurationsByDate durationsByDate : this.durationsByDate) {
			
			Date testResultDate = new Date(testResult.getEndTime());
			
			Date statisticsDate = durationsByDate.getDay();
			
			//if (durationsByDate.getDay().compareTo(new Date(testResult.getEndTime())) == 0) {
			if (DateUtil.compareByYMD(durationsByDate.getDay(), new Date(testResult.getEndTime()))) {
				durationsByDate.setTotalRuns(durationsByDate.getTotalRuns() + 1);
				if (testResult.getResult().equals(Result.SUCCESS)) {
					durationsByDate.getDurations().add(testResult.getEndTime() - testResult.getStartTime());
					return;
				}
			}
		}
		
		DurationsByDate durationsByDate = new DurationsByDate(testResult);
		this.durationsByDate.add(durationsByDate);
		
	}

	public ArrayList<DurationsByDate> getDurationsByDate() {
		return durationsByDate;
	}

	public void setDurationsByDate(ArrayList<DurationsByDate> durationsByDate) {
		this.durationsByDate = durationsByDate;
	}
	
/*	public void addTestCaseWithStatisticsDTO(TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		for (TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO : testSuiteWithStatisticsDTOs) {
			if (testSuiteWithStatisticsDTO.getName().equals(testCaseWithStatisticsDTO.getTestSuiteName())) {
				testSuiteWithStatisticsDTO.getTestCaseStats().add(testCaseWithStatisticsDTO);
				return;
			}
		}
		
		TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO = new TestSuiteWithStatisticsDTO(testCaseWithStatisticsDTO.getTestSuiteName(), testCaseWithStatisticsDTO);
		this.testSuiteWithStatisticsDTOs.add(testSuiteWithStatisticsDTO);
		
	}*/
}

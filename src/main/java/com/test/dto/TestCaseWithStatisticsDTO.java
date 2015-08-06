package com.test.dto;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TestCaseWithStatisticsDTO {
	@JsonIgnore
	private String testSuiteName;
	private String name;
	private Long totalRuns;
	private Long passed;
	private Long failed;
	private Long successfulMin;
	private Long successfulMax;
	private Long successfulAvg;
	
	public TestCaseWithStatisticsDTO() {
		
	}
	
	public TestCaseWithStatisticsDTO(String testSuiteName, String testCaseName, List<Long> durations, long totalRuns) {
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
		this.successfulAvg = sum/this.passed;
		this.successfulMax = Collections.max(durations);
		this.successfulMin = Collections.min(durations);
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getTotalRuns() {
		return totalRuns;
	}
	public void setTotalRuns(Long totalRuns) {
		this.totalRuns = totalRuns;
	}
	public Long getPassed() {
		return passed;
	}
	public void setPassed(Long passed) {
		this.passed = passed;
	}
	public Long getFailed() {
		return failed;
	}
	public void setFailed(Long failed) {
		this.failed = failed;
	}
	public Long getSuccessfulMin() {
		return successfulMin;
	}
	public void setSuccessfulMin(Long successfulMin) {
		this.successfulMin = successfulMin;
	}
	public Long getSuccessfulMax() {
		return successfulMax;
	}
	public void setSuccessfulMax(Long successfulMax) {
		this.successfulMax = successfulMax;
	}
	public Long getSuccessfulAvg() {
		return successfulAvg;
	}
	public void setSuccessfulAvg(Long successfulAvg) {
		this.successfulAvg = successfulAvg;
	}

	public String getTestSuiteName() {
		return testSuiteName;
	}

	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}
	
}

package com.test.dto;

public class TestCaseWithStatistics {
	
	private String name;
	private Long totalRuns;
	private Long passed;
	private Long failed;
	private String successfulMin;
	private String successfulMax;
	private String successfulAvg;
	
	
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
	public String getSuccessfulMin() {
		return successfulMin;
	}
	public void setSuccessfulMin(String successfulMin) {
		this.successfulMin = successfulMin;
	}
	public String getSuccessfulMax() {
		return successfulMax;
	}
	public void setSuccessfulMax(String successfulMax) {
		this.successfulMax = successfulMax;
	}
	public String getSuccessfulAvg() {
		return successfulAvg;
	}
	public void setSuccessfulAvg(String successfulAvg) {
		this.successfulAvg = successfulAvg;
	}
	
	
	
}

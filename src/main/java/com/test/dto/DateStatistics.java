package com.test.dto;

import java.util.Date;

public class DateStatistics {
	
	private Date date;
	private Long totalRuns;
	private Long passed;
	private Long failed;
	private Long successfulMin;
	private Long successfulMax;
	private Long successfulAvg;
	
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}

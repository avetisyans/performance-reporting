package com.test.dto;

import com.test.domain.TestCase;
import com.test.domain.TestCase.TestType;
import com.test.domain.TestResult;
import com.test.domain.TestResult.Result;

public class TestCaseWithResultDTO {
	private String testCaseName;
	private TestType testType;
	private Result result;
	private Long startTime;
	private Long endTime;
	private String duration;

	public TestCaseWithResultDTO(TestCase testCase, TestResult testResult) {
		this.setTestCaseName(testCase.getName());
		this.setTestType(testCase.getTestType());
		this.setResult(testResult.getResult());
		
		this.setStartTime(testResult.getStartTime());
		this.setEndTime(testResult.getEndTime());
		this.setDuration(testResult.getDuration());
	}

	public String getTestCaseName() {
		return testCaseName;
	}

	public void setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}

	public Long getStartTime() {
		return startTime;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public Long getEndTime() {
		return endTime;
	}

	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}

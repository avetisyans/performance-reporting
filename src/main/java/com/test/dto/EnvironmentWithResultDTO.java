package com.test.dto;

import java.util.List;

public class EnvironmentWithResultDTO {
	
	private String name;
	private List<TestCaseWithStatistics> testCaseData;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TestCaseWithStatistics> getTestCaseData() {
		return testCaseData;
	}
	
	public void setTestCaseData(List<TestCaseWithStatistics> testCaseData) {
		this.testCaseData = testCaseData;
	}
	
}

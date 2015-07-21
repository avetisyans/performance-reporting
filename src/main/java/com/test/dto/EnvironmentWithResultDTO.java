package com.test.dto;

import java.util.List;

public class EnvironmentWithResultDTO {
	
	private String name;
	private List<TestCaseWithStatisticsDTO> testCaseData;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TestCaseWithStatisticsDTO> getTestCaseData() {
		return testCaseData;
	}
	
	public void setTestCaseData(List<TestCaseWithStatisticsDTO> testCaseData) {
		this.testCaseData = testCaseData;
	}
	
}

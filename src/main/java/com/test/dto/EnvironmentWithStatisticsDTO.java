package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentWithStatisticsDTO {
	
	private String name;
	private List<TestSuiteWithStatisticsDTO> testSuites = new ArrayList<TestSuiteWithStatisticsDTO>();
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<TestSuiteWithStatisticsDTO> getTestSuites() {
		return testSuites;
	}
	
	public void setTestSuites(List<TestSuiteWithStatisticsDTO> testSuites) {
		this.testSuites = testSuites;
	}
}

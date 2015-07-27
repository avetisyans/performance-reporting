package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class EnvironmentWithStatisticsDTO {
	
	private String name;
	private List<TestSuiteWithStatisticsDTO> testSuites = new ArrayList<TestSuiteWithStatisticsDTO>();
	
	public EnvironmentWithStatisticsDTO(String name2, TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO) {
		this.name = name2;
		this.testSuites.add(testSuiteWithStatisticsDTO);
	}

	public EnvironmentWithStatisticsDTO(String name2, List<TestSuiteWithStatisticsDTO> testSuiteWithStatisticsDTOs) {
		this.name = name2;
		this.testSuites = testSuiteWithStatisticsDTOs;
	}

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

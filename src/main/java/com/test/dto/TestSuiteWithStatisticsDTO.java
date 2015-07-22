package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithStatisticsDTO {
	private String name;
	private List<TestCaseWithStatisticsDTO> testCaseStats = new ArrayList<TestCaseWithStatisticsDTO>();
	
	public TestSuiteWithStatisticsDTO(String testSuiteName, TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		this.name = testSuiteName;
		this.testCaseStats.add(testCaseWithStatisticsDTO);
	}

	public void addTestCaseWithStatisticsDTO(TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		if (!contains(testCaseWithStatisticsDTO)) {
			this.testCaseStats.add(testCaseWithStatisticsDTO);
		}
	}

	private boolean contains(TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		for (TestCaseWithStatisticsDTO tStatisticsDTO : testCaseStats) {
			if (tStatisticsDTO.getName().equals(testCaseWithStatisticsDTO.getName())) {
				return true;
			}
		}
		return false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<TestCaseWithStatisticsDTO> getTestCaseStats() {
		return testCaseStats;
	}

	public void setTestCaseStats(List<TestCaseWithStatisticsDTO> testCaseStats) {
		this.testCaseStats = testCaseStats;
	}

/*	public List<TestCaseWithStatisticsDTO> getTestCaseData() {
		return testCaseStats;
	}

	public void setTestCaseData(List<TestCaseWithStatisticsDTO> testCaseData) {
		this.testCaseStats = testCaseData;
	}*/
}

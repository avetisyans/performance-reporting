package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithStatisticsDTO {
	private String name;
	private List<TestCaseWithStatisticsDTO> testCaseData = new ArrayList<TestCaseWithStatisticsDTO>();
	
	public TestSuiteWithStatisticsDTO(String testSuiteName, TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		this.name = testSuiteName;
		this.testCaseData.add(testCaseWithStatisticsDTO);
	}

	public void addTestCaseWithStatisticsDTO(TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		if (!contains(testCaseWithStatisticsDTO)) {
			this.testCaseData.add(testCaseWithStatisticsDTO);
		}
	}

	private boolean contains(TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		for (TestCaseWithStatisticsDTO tStatisticsDTO : testCaseData) {
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

	public List<TestCaseWithStatisticsDTO> getTestCaseData() {
		return testCaseData;
	}

	public void setTestCaseData(List<TestCaseWithStatisticsDTO> testCaseData) {
		this.testCaseData = testCaseData;
	}
}

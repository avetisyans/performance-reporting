package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithStatisticsDTOWrapper {
	private List<TestSuiteWithStatisticsDTO> testSuiteWithStatisticsDTOs = new ArrayList<TestSuiteWithStatisticsDTO>();

	public List<TestSuiteWithStatisticsDTO> getTestSuiteWithStatisticsDTOs() {
		return testSuiteWithStatisticsDTOs;
	}

	public void setTestSuiteWithStatisticsDTOs(List<TestSuiteWithStatisticsDTO> testSuiteWithStatisticsDTOs) {
		this.testSuiteWithStatisticsDTOs = testSuiteWithStatisticsDTOs;
	}

	public void addTestSuiteWithStatisticsDTO(TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO) {
		
	}

	public void addTestCaseWithStatisticsDTO(TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		for (TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO : testSuiteWithStatisticsDTOs) {
			if (testSuiteWithStatisticsDTO.getName().equals(testCaseWithStatisticsDTO.getTestSuiteName())) {
				testSuiteWithStatisticsDTO.getTestCaseStats().add(testCaseWithStatisticsDTO);
				return;
			}
		}
		
		TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO = new TestSuiteWithStatisticsDTO(testCaseWithStatisticsDTO.getTestSuiteName(), testCaseWithStatisticsDTO);
		this.testSuiteWithStatisticsDTOs.add(testSuiteWithStatisticsDTO);
		
	}
	
	
}

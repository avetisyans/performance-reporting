package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithStatisticsEntity {
	private String testSuiteName;
	private TestCaseWithStatisticsDTO testCaseWithStatisticsDTO;
	

	public TestSuiteWithStatisticsEntity(String testSuiteName, TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		this.testSuiteName = testSuiteName;
		this.testCaseWithStatisticsDTO = testCaseWithStatisticsDTO;
	}


	public static List<TestSuiteWithStatisticsDTO> mapToTestSuiteWithStatisticsDTOs(List<TestSuiteWithStatisticsEntity> testSuiteWithStatisticsEntities) {
		List<TestSuiteWithStatisticsDTO> testSuiteWithStatisticsDTOs = new ArrayList<TestSuiteWithStatisticsDTO>();
		
		for (TestSuiteWithStatisticsEntity testSuiteWithStatisticsEntity : testSuiteWithStatisticsEntities) {
			boolean flag = false;
			for (TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO : testSuiteWithStatisticsDTOs) {
				if (testSuiteWithStatisticsDTO.getName() == testSuiteWithStatisticsEntity.getTestSuiteName()) {
					flag = true;
					testSuiteWithStatisticsDTO.addTestCaseWithStatisticsDTO(testSuiteWithStatisticsEntity.getTestCaseWithStatisticsDTO());
					break;
				}
			}
			
			if (flag == false) {
				TestSuiteWithStatisticsDTO testSuiteWithStatisticsDTO = new TestSuiteWithStatisticsDTO(testSuiteWithStatisticsEntity.getTestSuiteName(), testSuiteWithStatisticsEntity.getTestCaseWithStatisticsDTO());
				testSuiteWithStatisticsDTOs.add(testSuiteWithStatisticsDTO);
			}
		}
		return testSuiteWithStatisticsDTOs;
	}


	public String getTestSuiteName() {
		return testSuiteName;
	}


	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}


	public TestCaseWithStatisticsDTO getTestCaseWithStatisticsDTO() {
		return testCaseWithStatisticsDTO;
	}


	public void setTestCaseWithStatisticsDTO(
			TestCaseWithStatisticsDTO testCaseWithStatisticsDTO) {
		this.testCaseWithStatisticsDTO = testCaseWithStatisticsDTO;
	}
}

package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithResultEntity {
	
	private String testSuiteName;
	private TestCaseWithResultDTO testCaseWithResultDTO;
	

	public TestSuiteWithResultEntity(String testSuiteName2, TestCaseWithResultDTO testCaseWithResultDTO2) {
		this.testSuiteName = testSuiteName2;
		this.testCaseWithResultDTO = testCaseWithResultDTO2;
	}


	public static List<TestSuiteWithResultDTO> mapToTestSuiteDTOs(List<TestSuiteWithResultEntity> testSuiteWithResultEntities) {
		List<TestSuiteWithResultDTO> testSuiteWithResultDTOs = new ArrayList<TestSuiteWithResultDTO>();
		
		for (TestSuiteWithResultEntity testSuiteWithResultEntity : testSuiteWithResultEntities) {
			boolean flag = false;
			for (TestSuiteWithResultDTO testSuiteDTO3 : testSuiteWithResultDTOs) {
				if (testSuiteDTO3.getName() == testSuiteWithResultEntity.getTestSuiteName()) {
					flag = true;
					testSuiteDTO3.addTestCaseWithResultDTO(testSuiteWithResultEntity.getTestCaseWithResultDTO());
					break;
				}
			}
			
			if (flag == false) {
				TestSuiteWithResultDTO testSuiteDTO2 = new TestSuiteWithResultDTO(testSuiteWithResultEntity.getTestSuiteName(), testSuiteWithResultEntity.getTestCaseWithResultDTO());
				testSuiteWithResultDTOs.add(testSuiteDTO2);
			}
		}
		return testSuiteWithResultDTOs;
	}


	public String getTestSuiteName() {
		return testSuiteName;
	}


	public void setTestSuiteName(String testSuiteName) {
		this.testSuiteName = testSuiteName;
	}


	public TestCaseWithResultDTO getTestCaseWithResultDTO() {
		return testCaseWithResultDTO;
	}


	public void setTestCaseWithResultDTO(TestCaseWithResultDTO testCaseWithResultDTO) {
		this.testCaseWithResultDTO = testCaseWithResultDTO;
	}

}

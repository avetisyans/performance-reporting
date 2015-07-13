package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestSuiteWithResult {
	
	private String testSuiteName;
	private TestCaseWithResultDTO testCaseWithResultDTO;
	

	public TestSuiteWithResult(String testSuiteName2, TestCaseWithResultDTO testCaseWithResultDTO2) {
		this.testSuiteName = testSuiteName2;
		this.testCaseWithResultDTO = testCaseWithResultDTO2;
	}


	public static List<TestSuiteDTO> mapToTestSuiteDTOs(List<TestSuiteWithResult> testSuiteWithResults) {
		List<TestSuiteDTO> testSuiteDTOs = new ArrayList<TestSuiteDTO>();
		
		for (TestSuiteWithResult testSuiteWithResult : testSuiteWithResults) {
			boolean flag = false;
			for (TestSuiteDTO testSuiteDTO3 : testSuiteDTOs) {
				if (testSuiteDTO3.getName() == testSuiteWithResult.getTestSuiteName()) {
					flag = true;
					testSuiteDTO3.addTestCaseWithResultDTO(testSuiteWithResult.getTestCaseWithResultDTO());
					break;
				}
			}
			
			if (flag == false) {
				TestSuiteDTO testSuiteDTO2 = new TestSuiteDTO(testSuiteWithResult.getTestSuiteName(), testSuiteWithResult.getTestCaseWithResultDTO());
				testSuiteDTOs.add(testSuiteDTO2);
			}
		}
		return testSuiteDTOs;
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

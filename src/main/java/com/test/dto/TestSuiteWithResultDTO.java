package com.test.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.domain.TestSuite;
import com.test.service.TestResultService;

public class TestSuiteWithResultDTO {
	
	private String name;
	private List<TestCaseWithResultDTO> testCases = new ArrayList<TestCaseWithResultDTO>();
	
	
/*	public TestSuiteWithResultDTO(TestSuite testSuite) {
		this.name = testSuite.getName();
		
	}*/

	public TestSuiteWithResultDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public void addTestCaseWithResultDTO(TestCaseWithResultDTO testCaseWithResultDTO) {
		if (!contains(testCaseWithResultDTO)) {
			this.testCases.add(testCaseWithResultDTO);
		}
	}
	
	public TestSuiteWithResultDTO(String testSuiteName, TestCaseWithResultDTO testCaseWithResultDTO) {
		this.name = testSuiteName;
		this.testCases.add(testCaseWithResultDTO);
	}

	public boolean contains(TestCaseWithResultDTO tDTO) {
		for (TestCaseWithResultDTO testCaseWithResultDTO : testCases) {
			if (testCaseWithResultDTO.getTestCaseName().equals(tDTO.getTestCaseName())) {
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
	
	public List<TestCaseWithResultDTO> getTestCases() {
		return testCases;
	}
	
	public void setTestCases(List<TestCaseWithResultDTO> testCases) {
		this.testCases = testCases;
	}

	public static TestSuiteWithResultDTO mapToDTO(TestSuite testSuite) {
		
		List<TestCase> testCases= testSuite.getTestCases();
		for (TestCase testCase : testCases) {
			
			System.out.println(testCase.getName());
			
		}
		
		return null;
		
	}
}

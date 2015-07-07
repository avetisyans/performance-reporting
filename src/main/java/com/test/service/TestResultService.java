package com.test.service;

import java.util.List;

import com.test.domain.TestCase;
import com.test.domain.TestResult;

public interface TestResultService {

	List<TestResult> findAll();
	
	TestResult save(TestResult testResult);
	
	TestResult findOne(Long id);
	
	List<TestResult> findTestResultByName();

	//TestResult findByTestCase(Long testCaseId);

	TestResult findByTestCaseAndRun(Long testCaseId, Long runId);
	
	
}

package com.test.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.test.domain.TestCase;
import com.test.domain.TestResult;

public interface TestResultService {

	List<TestResult> findAll();
	
	TestResult save(TestResult testResult);
	
	TestResult findOne(Long id);
	
	List<TestResult> findTestResultByName();

	//TestResult findByTestCase(Long testCaseId);

	TestResult findByTestCaseAndRun(Long testCaseId, Long runId);
	
	List<TestResult> findByEnvAndTestCase(Long envId, Long testCaseId, Pageable pageable);
	
	List<TestResult> findByEnvAndTestCaseForRecentDays(Long envId, Long testCaseId, Long long1); 
	
	
}

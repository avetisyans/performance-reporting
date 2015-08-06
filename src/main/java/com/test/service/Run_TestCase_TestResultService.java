package com.test.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.test.domain.Run;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;

public interface Run_TestCase_TestResultService {
	
	List<Run_TestCase_TestResult> findAll();
	Run_TestCase_TestResult save(Run_TestCase_TestResult run_TestCase_TestResult);
/*	Env_TestCase_TestResult saveWithEntities(Environment environment, TestResult testResult,
			TestCase testCase);*/
	Run_TestCase_TestResult findByRunAndTestCase(Run run, TestCase testCase);
	
	List<Run_TestCase_TestResult> findByEnvironment(Long envId, Pageable pageable);
}



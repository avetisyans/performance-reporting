package com.test.service;

import java.util.List;

import com.test.domain.Run_TestCase_TestResult;

public interface Run_TestCase_TestResultService {
	
	List<Run_TestCase_TestResult> findAll();
	Run_TestCase_TestResult save(Run_TestCase_TestResult run_TestCase_TestResult);
/*	Env_TestCase_TestResult saveWithEntities(Environment environment, TestResult testResult,
			TestCase testCase);*/
	
}



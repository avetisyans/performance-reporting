package com.test.service;

import java.util.List;

import com.test.domain.Env_TestCase_TestResult;
import com.test.domain.Environment;
import com.test.domain.TestCase;
import com.test.domain.TestResult;

public interface Env_TestCase_TestResultService {
	
	List<Env_TestCase_TestResult> findAll();
	Env_TestCase_TestResult save(Env_TestCase_TestResult env_TestCase_TestResult);
	Env_TestCase_TestResult saveWithEntities(Environment environment, TestResult testResult,
			TestCase testCase);
	
}



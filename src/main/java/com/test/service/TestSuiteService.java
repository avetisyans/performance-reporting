package com.test.service;

import java.util.List;

import com.test.domain.TestSuite;

public interface TestSuiteService {

	List<TestSuite> findAll();
	
	TestSuite save(TestSuite testSuite);

	TestSuite findByName(String name);

	TestSuite saveToDB(TestSuite testSuite);
	
	List<TestSuite> findByRun(Long runId);
	
}

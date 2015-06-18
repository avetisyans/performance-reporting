package com.test.service;

import java.util.List;

import com.test.domain.TestSuite;

public interface TestSuiteService {

	List<TestSuite> findAll();
	
	TestSuite save(TestSuite testSuite);

	TestSuite save(String name, TestSuite testSuite);

	TestSuite findByName(String name);

	TestSuite save(TestSuite testSuite, String name);

	TestSuite saveToItsRun(TestSuite testSuite);
	
}

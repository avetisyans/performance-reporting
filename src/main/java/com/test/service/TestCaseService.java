package com.test.service;

import java.util.List;

import com.test.domain.TestCase;

public interface TestCaseService {
	List<TestCase> findAll();
	
	TestCase save(TestCase testCase);

	TestCase findByName(String name);

	TestCase saveToItsTestSuite(TestCase testCase);

}

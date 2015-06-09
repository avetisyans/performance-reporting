package com.test.service;

import java.util.List;

import com.test.domain.TestCase;

public interface TestCaseService {
	List<TestCase> findAll();
	
	TestCase save(TestCase testCase);

	TestCase save(String name, TestCase testCase);

	TestCase findByName(String name);

}

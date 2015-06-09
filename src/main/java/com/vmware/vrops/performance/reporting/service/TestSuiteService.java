package com.vmware.vrops.performance.reporting.service;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.TestSuite;

public interface TestSuiteService {

	void save(TestSuite testSuite);

	TestSuite update(TestSuite testSuite);

	void delete(TestSuite testSuite);

	TestSuite findById(Long id);

	List<TestSuite> findByName(String name);

	List<TestSuite> findAll();

	List<String> findSuiteNames();
}

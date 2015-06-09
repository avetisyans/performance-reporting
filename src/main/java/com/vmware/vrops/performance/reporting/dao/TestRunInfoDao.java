package com.vmware.vrops.performance.reporting.dao;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.TestRunInfo;

public interface TestRunInfoDao {


	void save(TestRunInfo testRunInfo);

	TestRunInfo update(TestRunInfo testRunInfo);

	void delete(TestRunInfo testRunInfo);

	TestRunInfo findById(Long id);

	List<TestRunInfo> findByTestDefName(String name);

	List<TestRunInfo> findAll();

	List<TestRunInfo> findByTestDefNameAndEnv(String testDefName,
			String environmentName);

}

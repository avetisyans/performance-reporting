package com.vmware.vrops.performance.reporting.dao;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.TestListRunInfo;

public interface TestListRunInfoDao {

	void save(TestListRunInfo testListRunInfo);

	TestListRunInfo update(TestListRunInfo testListRunInfo);

	void delete(TestListRunInfo testListRunInfo);

	TestListRunInfo findById(Long id);

	List<TestListRunInfo> findByName(String name);

	List<TestListRunInfo> findAll();

	List<TestListRunInfo> findBySuiteId(String suiteId);
}

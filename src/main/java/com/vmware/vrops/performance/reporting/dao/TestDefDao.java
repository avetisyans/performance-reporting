package com.vmware.vrops.performance.reporting.dao;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.TestDef;

public interface TestDefDao {

	List<TestDef> findAll();

	void save(TestDef testDef);

}

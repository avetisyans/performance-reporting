package com.vmware.vrops.performance.reporting.service;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.TestDef;


public interface TestDefService {

	void save(TestDef testDef);
	List<TestDef> findAll();

}

/*package com.vmware.vrops.performance.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vrops.performance.reporting.dao.DailyRunInfoDao;
import com.vmware.vrops.performance.reporting.dao.TestDefDao;
import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.domain.TestDef;
import com.vmware.vrops.performance.reporting.service.DailyRunInfoService;
import com.vmware.vrops.performance.reporting.service.TestDefService;


@Service("testDefService")
@Transactional
public class TestDefServiceImpl implements TestDefService {


	@Autowired
	private TestDefDao testDefDao;

	@Override
	public void save(TestDef testDef) {
		testDefDao.save(testDef);
	}

	@Override
	public List<TestDef> findAll() {
		return testDefDao.findAll();
	}


}
*/
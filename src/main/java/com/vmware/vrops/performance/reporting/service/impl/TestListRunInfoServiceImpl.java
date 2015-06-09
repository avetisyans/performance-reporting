package com.vmware.vrops.performance.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vrops.performance.reporting.dao.TestRunInfoDao;
import com.vmware.vrops.performance.reporting.dao.TestListRunInfoDao;
import com.vmware.vrops.performance.reporting.domain.TestRunInfo;
import com.vmware.vrops.performance.reporting.domain.TestListRunInfo;
import com.vmware.vrops.performance.reporting.service.TestListRunInfoService;


@Service("testListRunInfoService")
@Transactional
public class TestListRunInfoServiceImpl implements TestListRunInfoService { //TestService<TestListRunInfo>{

	@Autowired
	private TestListRunInfoDao testListRunInfoDao;


	@Override
	public void save(TestListRunInfo testListRunInfo) {
		testListRunInfoDao.save(testListRunInfo);
	}

	@Override
	public TestListRunInfo update(TestListRunInfo testListRunInfo) {
		return testListRunInfoDao.update(testListRunInfo);
	}

	@Override
	public void delete(TestListRunInfo testListRunInfo) {
		testListRunInfoDao.delete(testListRunInfo);
	}

	@Override
	public TestListRunInfo findById(Long id) {
		return testListRunInfoDao.findById(id);
	}

	@Override
	public List<TestListRunInfo> findByName(String name) {
		return testListRunInfoDao.findByName(name);
	}

	@Override
	public List<TestListRunInfo> findAll() {
		return testListRunInfoDao.findAll();
	}

	public TestListRunInfoDao getTestListRunInfoDao() {
		return testListRunInfoDao;
	}

	public void setTestListRunInfoDao(TestListRunInfoDao testListRunInfoDao) {
		this.testListRunInfoDao = testListRunInfoDao;
	}

	@Override
	public List<TestListRunInfo> findBySuiteId(String suiteId) {
		return testListRunInfoDao.findBySuiteId(suiteId);
	}


}

package com.vmware.vrops.performance.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vrops.performance.reporting.dao.TestRunInfoDao;
import com.vmware.vrops.performance.reporting.domain.TestRunInfo;
import com.vmware.vrops.performance.reporting.service.TestRunInfoService;

@Service("testRunInfoService")
@Transactional
public class TestRunInfoServiceImpl implements TestRunInfoService {// TestService<TestRunInfo>{

	@Autowired
	private TestRunInfoDao testRunInfoDao;

	@Override
	public void save(TestRunInfo testRunInfo) {
		testRunInfoDao.save(testRunInfo);
	}

	@Override
	public TestRunInfo update(TestRunInfo testRunInfo) {
		return testRunInfoDao.update(testRunInfo);
	}

	@Override
	public void delete(TestRunInfo testRunInfo) {
		testRunInfoDao.delete(testRunInfo);
	}

	@Override
	public TestRunInfo findById(Long id) {
		return testRunInfoDao.findById(id);
	}

	@Override
	public List<TestRunInfo> findByTestDefName(String name) {
		return testRunInfoDao.findByTestDefName(name);
	}

	@Override
	public List<TestRunInfo> findAll() {
		return testRunInfoDao.findAll();
	}

	public TestRunInfoDao getTestRunInfoDao() {
		return testRunInfoDao;
	}

	public void setTestRunInfoDao(TestRunInfoDao testRunInfoDao) {
		this.testRunInfoDao = testRunInfoDao;
	}

	@Override
	public List<TestRunInfo> findByTestDefNameAndEnv(String testDefName,
			String environmentName) {
		return testRunInfoDao.findByTestDefNameAndEnv(testDefName,
				environmentName);
	}

}

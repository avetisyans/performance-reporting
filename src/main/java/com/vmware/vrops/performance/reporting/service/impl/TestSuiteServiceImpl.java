package com.vmware.vrops.performance.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vrops.performance.reporting.dao.TestSuiteDao;
import com.vmware.vrops.performance.reporting.domain.TestSuite;
import com.vmware.vrops.performance.reporting.service.TestSuiteService;

@Service("testSuiteInfoService")
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService { //TestService<TestSuite>{

		@Autowired
		private TestSuiteDao testSuiteDao;


		@Override
		public void save(TestSuite testSuite) {
			testSuiteDao.save(testSuite);
		}

		@Override
		public TestSuite update(TestSuite testSuite) {
			return testSuiteDao.update(testSuite);
		}

		@Override
		public void delete(TestSuite testSuite) {
			testSuiteDao.delete(testSuite);
		}

		@Override
		public TestSuite findById(Long id) {
			return testSuiteDao.findById(id);
		}

		@Override
		public List<TestSuite> findByName(String name) {
			return testSuiteDao.findByName(name);
		}

		@Override
		public List<TestSuite> findAll() {
			return testSuiteDao.findAll();
		}

		public TestSuiteDao getTestSuiteDao() {
			return testSuiteDao;
		}

		public void setTestSuiteDao(TestSuiteDao testSuiteDao) {
			this.testSuiteDao = testSuiteDao;
		}

		@Override
		public List<String> findSuiteNames() {
			return testSuiteDao.findSuiteNames();
		}


	}


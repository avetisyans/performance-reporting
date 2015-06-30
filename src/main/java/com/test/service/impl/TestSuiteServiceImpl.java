package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.RunDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Run;
import com.test.domain.TestSuite;
import com.test.service.RunService;
import com.test.service.TestSuiteService;

@Service("testSuiteService")
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService {
	
	@Autowired
	private TestSuiteDao testSuiteDao;

	@Autowired
	private RunService runService;
	
	@Autowired
	private RunDao runDao;
	
	@Override
	public List<TestSuite> findAll() {
		return testSuiteDao.findAll();
	}

	@Override
	public TestSuite save(TestSuite testSuite) {
		return testSuiteDao.save(testSuite);
	}

	@Override
	public TestSuite findByName(String name) {
		return testSuiteDao.findByName(name);
	}

	@Override
	public TestSuite saveToDB(TestSuite testSuite) {
		//TestSuite testSuiteFromDB = testSuiteDao.findByName(testSuite.getName());
		//Run runFromTestSuite = testSuite.getRuns().get(0);
		//Run savedRun = runService.saveToItsParent(runFromTestSuite);
		TestSuite testSuiteFromDB = testSuiteDao.findByName(testSuite.getName());
		
		return testSuiteFromDB != null ? testSuiteFromDB : testSuiteDao.save(testSuite);

	}
	
}


package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TestSuiteDao;
import com.test.domain.TestCase;
import com.test.domain.TestSuite;
import com.test.service.TestSuiteService;

@Service("testSuiteService")
@Transactional
public class TestSuiteServiceImpl implements TestSuiteService {
	
	@Autowired
	private TestSuiteDao testSuiteDao;

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
		System.out.println("aaaaaa_________finding________________++++++++++++++");
		return testSuiteDao.findByName(name);
	}

	@Override
	public TestSuite save(String name, TestSuite testSuite) {
		TestSuite findedTestSuite = testSuiteDao.findByName(name);
		
		if (findedTestSuite == null) {
			return testSuiteDao.save(testSuite);
		}
		
		return null;
	}

	@Override
	public TestSuite save(TestSuite testSuite, String name) {
		TestSuite foundTestSuite = testSuiteDao.findByName(name);
		
		if (foundTestSuite == null) {
			System.out.println("Saving Test Suite");
			return testSuiteDao.save(testSuite);
		}

		return null;
	}
	
}


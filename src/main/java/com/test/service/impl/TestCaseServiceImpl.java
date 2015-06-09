package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TestCaseDao;
import com.test.domain.TestCase;
import com.test.domain.TestSuite;
import com.test.service.TestCaseService;

@Service("testCaseService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

	@Autowired
	private TestCaseDao testCaseDao;
	
	@Override
	public List<TestCase> findAll() {
		return testCaseDao.findAll();
	}

	@Override
	public TestCase save(TestCase testCase) {
		return testCaseDao.save(testCase);
	}

	@Override
	public TestCase save(String name, TestCase testCase) {
		TestCase findedTestCase = testCaseDao.findByName(name);
		
		if (findedTestCase == null) {
			return testCaseDao.save(testCase);
		}
		
		return null;
	}

	@Override
	public TestCase findByName(String name) {
		return testCaseDao.findByName(name);
	}




}

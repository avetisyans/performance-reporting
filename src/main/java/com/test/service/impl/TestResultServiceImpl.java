package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TestResultDao;
import com.test.domain.TestResult;
import com.test.service.TestResultService;

@Service("testResultService")
@Transactional
public class TestResultServiceImpl implements TestResultService {

	@Autowired
	private TestResultDao testResultDao;
	
	@Override
	public List<TestResult> findAll() {
		return testResultDao.findAll();
	}

	@Override
	public TestResult save(TestResult testResult) {
		return testResultDao.save(testResult);
	}

	@Override
	public List<TestResult> findTestResultByName() {
		// TODO Auto-generated method stub
		//alskdjfas
		return null;
	}
	

	@Override
	public TestResult findOne(Long id) {
		return testResultDao.findOne(id);
	}
	

/*	@Override
	public List<TestResult> findTestResultByName() {
		return testResultDao.findTestResultByName();
	}*/

}

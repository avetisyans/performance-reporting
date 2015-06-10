package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.Env_TestCase_TestResultDao;
import com.test.dao.EnvironmentDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.domain.Env_TestCase_TestResult;
import com.test.domain.Environment;
import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.service.Env_TestCase_TestResultService;

@Service("env_TestCase_TestResultService")
@Transactional
public class Env_TestCase_TestResultServiceImpl implements Env_TestCase_TestResultService {

	@Autowired
	private Env_TestCase_TestResultDao env_TestCase_TestResultDao;
	
	@Autowired
	private EnvironmentDao environmentDao;

	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	private TestResultDao testResultDao;
	
	
	@Override
	public List<Env_TestCase_TestResult> findAll() {
		return env_TestCase_TestResultDao.findAll();
	}

	@Override
	public Env_TestCase_TestResult save(Env_TestCase_TestResult env_TestCase_TestResult) {
		return env_TestCase_TestResultDao.save(env_TestCase_TestResult);
	}

	@Override
	public Env_TestCase_TestResult saveWithEntities(Environment environment, TestResult testResult, TestCase testCase) {
		Env_TestCase_TestResult env_TestCase_TestResult = new Env_TestCase_TestResult();
		environmentDao.save(environment);
		testResultDao.save(testResult);
		
		env_TestCase_TestResult.setEnvironment(environment);
		env_TestCase_TestResult.setTestCase(testCase);
		env_TestCase_TestResult.setTestResult(testResult);
		
		return env_TestCase_TestResultDao.save(env_TestCase_TestResult);
	}

}


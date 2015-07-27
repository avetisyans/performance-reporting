package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.EnvironmentDao;
import com.test.dao.Run_TestCase_TestResultDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.domain.Run;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;
import com.test.service.Run_TestCase_TestResultService;

@Service("run_TestCase_TestResultService")
@Transactional
public class Run_TestCase_TestResultServiceImpl implements Run_TestCase_TestResultService {

	@Autowired
	private Run_TestCase_TestResultDao run_TestCase_TestResultDao;
	
	@Autowired
	private EnvironmentDao environmentDao;

	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	private TestResultDao testResultDao;
	
	
	@Override
	public List<Run_TestCase_TestResult> findAll() {
		return run_TestCase_TestResultDao.findAll();
	}

	@Override
	public Run_TestCase_TestResult save(Run_TestCase_TestResult run_TestCase_TestResult) {
		return run_TestCase_TestResultDao.save(run_TestCase_TestResult);
	}

	@Override
	public Run_TestCase_TestResult findByRunAndTestCase(Run run, TestCase testCase) {
		return run_TestCase_TestResultDao.findByRunAndTestCase(run, testCase);
	}

/*	@Override
	public Env_TestCase_TestResult saveWithEntities(Environment environment, TestResult testResult, TestCase testCase) {
		Env_TestCase_TestResult env_TestCase_TestResult = new Env_TestCase_TestResult();
		environmentDao.save(environment);
		testResultDao.save(testResult);
		
		env_TestCase_TestResult.setEnvironment(environment);
		env_TestCase_TestResult.setTestCase(testCase);
		env_TestCase_TestResult.setTestResult(testResult);
		
		return env_TestCase_TestResultDao.save(env_TestCase_TestResult);
	}*/

}


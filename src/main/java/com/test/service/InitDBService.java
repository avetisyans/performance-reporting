package com.test.service;

import java.sql.Timestamp;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.Env_TestCase_TestResultDao;
import com.test.dao.EnvironmentDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Env_TestCase_TestResult;
import com.test.domain.Environment;
import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.domain.TestResult.TestType;
import com.test.domain.TestSuite;

@Service
@Transactional
public class InitDBService {

	@Autowired
	private Env_TestCase_TestResultDao env_TestCase_TestResultDao;

	@Autowired
	private EnvironmentDao environmentDao;

	@Autowired
	private TestCaseDao testCaseDao;

/*	@Autowired
	private TestClassDao testClassDao;*/
	
	
	@Autowired
	private TestResultDao testResultDao;

/*	@Autowired
	private TestSetDao testSetDao;*/

	@Autowired
	private TestSuiteDao testSuiteDao;

	//@PostConstruct
	public void init() {
		
/*		TestResult testResult = new TestResult();
		testResult.setName("aaa");
		testResult.setStartTime("START_TIME_10000000001");
		testResult.setEndTime("END_TIME_2");
		testResultDao.save(testResult);
		
		Environment env = new Environment();
		env.setName("ENV_1");
		env.setBuildNumber("9999999");
		env.setTestResult(testResult);
		environmentDao.save(env); */
		
/*		XMLSuite testSuite = new XMLSuite();
		testSuite.setName("First Test Suite");
		testSuiteDao.save(testSuite);
		
		
		TestSet testSet = new TestSet();
		testSet.setName("TestSet 1 of First Test Suite");
		testSet.setTestSuite(testSuite);
		testSetDao.save(testSet);*/
		
		/*******      First Stage         ****/
		
		TestSuite testSuite1 = new TestSuite();
		testSuite1.setName("TestSuite 1");
		//testSuiteDao.save(testSuite1);
		
		TestCase testCase1 = new TestCase();
		testCase1.setName("TestCase 1 of TestSuite 1");
		testCase1.setTestSuite(testSuite1);
		//testCaseDao.save(testCase1);
		
		Environment environment = new Environment();
		environment.setBranch("Branch1");
		environment.setBuildNumber("1122211");
		environment.setHaEnabled(true);
		environment.setName("Environment 1");
		//environmentDao.save(environment);
		
		//environment.setName("After save");
		
		
		TestResult testResult1 = new TestResult();
		testResult1.setName("Test Result 1");
		testResult1.setResult("Success");
		testResult1.setStartTime(new Timestamp(new Date().getTime()));
		testResult1.setEndTime(new Timestamp(new Date().getTime()));
		testResult1.setTestType(TestType.API);
		//testResultDao.save(testResult1);
		
		// Link Env, TestResult and TestCase
		
		Env_TestCase_TestResult env_TestCase_TestResult = new Env_TestCase_TestResult();
		//env_TestCase_TestResult.setTestCase(testCase1);
		env_TestCase_TestResult.setEnvironment(environment);
		//env_TestCase_TestResult.setTestResult(testResult1);
		//env_TestCase_TestResultDao.save(env_TestCase_TestResult);
	//	testSuiteDao.save(testSuite1);
	//	testCaseDao.save(testCase1);
	//	environmentDao.save(environment);
	//	testResultDao.save(testResult1);
/*		// SECOND Stage
		
		TestSuite testSuite2 = new TestSuite();
		testSuite2.setName("TestSuite 2");
		testSuiteDao.save(testSuite2);
		
		TestCase testCase2 = new TestCase();
		testCase2.setName("TestCase 2 of TestSuite 2");
		testCase2.setTestSuite(testSuite2);
		testCaseDao.save(testCase2);
		
		TestCase testCase3 = new TestCase();
		testCase3.setName("TestCase 3 of TestSuite 2");
		testCase3.setTestSuite(testSuite2);
		testCaseDao.save(testCase3);
		
		
		
		Environment environment2 = new Environment();
		environment2.setBranch("Branch2");
		environment2.setBuildNumber("424422");
		environment2.setHaEnabled(true);
		environment2.setName("Environment 2");
		environmentDao.save(environment2);
		
		
		
		TestResult testResult2 = new TestResult();
		testResult2.setName("Test Result 2");
		testResult2.setResult("Success 2");
		testResult2.setStartTime(new Date());
		testResult2.setEndTime(new Date());
		testResult2.setTestType(TestType.UI);
		testResultDao.save(testResult2);
		
		
		TestResult testResult3 = new TestResult();
		testResult3.setName("Test Result 3");
		testResult3.setResult("Success 2");
		testResult3.setStartTime(new Date());
		testResult3.setEndTime(new Date());
		testResult3.setTestType(TestType.UI);
		testResultDao.save(testResult3);
		
		
		
		
		
		Env_TestCase_TestResult env_TestCase_TestResult2 = new Env_TestCase_TestResult();
		env_TestCase_TestResult2.setEnvironment(environment2);
		env_TestCase_TestResult2.setTestCase(testCase2);
		env_TestCase_TestResult2.setTestResult(testResult2);
		env_TestCase_TestResultDao.save(env_TestCase_TestResult2);
		
		
		Env_TestCase_TestResult env_TestCase_TestResult3 = new Env_TestCase_TestResult();
		env_TestCase_TestResult3.setEnvironment(environment2);
		env_TestCase_TestResult3.setTestCase(testCase3);
		env_TestCase_TestResult3.setTestResult(testResult3);
		env_TestCase_TestResultDao.save(env_TestCase_TestResult3);*/
		
		
	}
}

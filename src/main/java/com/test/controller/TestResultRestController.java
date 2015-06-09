package com.test.controller;

import java.util.ArrayList;
import java.util.List;

import org.hsqldb.lib.ArrayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.dao.Env_TestCase_TestResultDao;
import com.test.dao.EnvironmentDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Env_TestCase_TestResult;
import com.test.domain.Environment;
import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.service.Env_TestCase_TestResultService;
import com.test.service.EnvironmentService;
import com.test.service.TestCaseService;
import com.test.service.TestResultService;
import com.test.service.TestSuiteService;

@RestController
@RequestMapping("/rest")
public class TestResultRestController {
	
	@Autowired
	private Env_TestCase_TestResultService env_TestCase_TestResultService;
	
	@Autowired
	private EnvironmentService environmentService;
	
	@Autowired
	private TestCaseService testCaseService;	
	
	@Autowired
	private TestResultService testResultService;
	
	@Autowired
	private TestSuiteService testSuiteService;
	
	@Autowired
	private Env_TestCase_TestResultDao env_TestCase_TestResultDao;
	
	@Autowired
	private EnvironmentDao environmentDao;
	
	@Autowired
	private TestCaseDao testCaseDao;	
	
	@Autowired
	private TestResultDao testResultDao;
	
	@Autowired
	private TestSuiteDao testSuiteDao;
	
	
	@RequestMapping(value="/testJson", method=RequestMethod.POST) 
	public JsonNode showTestClass(@RequestBody JsonNode envObj) {
		
		return envObj;
	}
	
	@RequestMapping(value="/testData", method=RequestMethod.POST) 
	public Env_TestCase_TestResult showTestClass(@RequestBody Env_TestCase_TestResult env_TestCase_TestResult) {
		
		
		
/*		Environment environment = new Environment(); env_TestCase_TestResult.getEnvironment();
		TestCase testCase = new TestCase(); env_TestCase_TestResult.getTestCase();
		TestResult testResult = new TestResult(); env_TestCase_TestResult.getTestResult();*/
	//	TestSuite testSuite = /*new TestSuite();*/ testCase.getTestSuite();
		//testCase.setName("FFFtestCase");
		//testCase.setTestSuite(testSuite);
		//environment.setName("FFFenv");
/*		testSuiteService.save(testSuite);
		testResultService.save(testResult);
		testCaseService.save(testCase);
		environmentService.save(environment);*/
		
/*		TestCase testCase1 = testCaseDao.findOne((long) 1);
		testCase1.setName("aaa");
		testCaseDao.save(testCase1);*/
		
		//Environment environment1 = environmentDao.findOne((long) 1);
		
		//Env_TestCase_TestResult newEnv_Test_Result = new Env_TestCase_TestResult();
		
/*		env_TestCase_TestResult.setEnvironment(environment);
		
		env_TestCase_TestResult.setTestResult(testResult);
		
		List<Env_TestCase_TestResult> env_TestCase_TestResults = new ArrayList<Env_TestCase_TestResult>();
		env_TestCase_TestResults.add(env_TestCase_TestResult);
		testCase1.setEnv_TestCase_TestResults(env_TestCase_TestResults);
		
		testCase1.setEnv_TestCase_TestResults(env_TestCase_TestResults);
		testCaseDao.save(testCase1);
		env_TestCase_TestResult.setTestCase(testCase1);*/
		env_TestCase_TestResultService.save(env_TestCase_TestResult);
		
		
		//System.out.println("finished!!!");
		return env_TestCase_TestResult;
	}
	
	
	
}



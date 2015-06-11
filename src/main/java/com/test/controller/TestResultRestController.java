package com.test.controller;

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

	@RequestMapping(value = "/testJson", method = RequestMethod.POST)
	public JsonNode showTestClass(@RequestBody JsonNode envObj) {

		return envObj;
	}

	@RequestMapping(value = "/testData", method = RequestMethod.POST)
	public String showResultJson(@RequestBody Env_TestCase_TestResult env_TestCase_TestResult) {

		Environment environment = env_TestCase_TestResult.getEnvironment();
		TestResult testResult = env_TestCase_TestResult.getTestResult();
		TestCase testCase = env_TestCase_TestResult.getTestCase();
		
		TestCase testCaseDB = testCaseService.saveToItsTestSuite(testCase);
		Environment environmentDB = environmentService.saveToDB(environment);
		testResultService.save(testResult);
		
		
		env_TestCase_TestResult.setEnvironment(environmentDB);
		env_TestCase_TestResult.setTestResult(testResult);
		env_TestCase_TestResult.setTestCase(testCaseDB);
		
		env_TestCase_TestResultService.save(env_TestCase_TestResult);

		return env_TestCase_TestResult.toString();
	}

}

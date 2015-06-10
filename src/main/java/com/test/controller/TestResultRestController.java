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
import com.test.domain.TestSuite;
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
	public Env_TestCase_TestResult showTestClass(
			@RequestBody Env_TestCase_TestResult env_TestCase_TestResult) {

		Environment environment = env_TestCase_TestResult.getEnvironment();
		TestResult testResult = env_TestCase_TestResult.getTestResult();
		TestCase testCase = env_TestCase_TestResult.getTestCase();
		TestSuite testSuite = testCase.getTestSuite();

		if (testSuiteService.save(testSuite, testSuite.getName()) == null) {

			if (testCaseService.save(testCase, testCase.getName()) == null) {

				TestCase findedTestCase = testCaseService.findByName(testCase
						.getName());

				System.out.println("finded is: " + findedTestCase);

				Env_TestCase_TestResult completeResult = env_TestCase_TestResultService
						.saveWithEntities(environment, testResult,
								findedTestCase);

				// env_TestCase_TestResultService.save(completeResult);

				// env_TestCase_TestResult.setEnvironment(completeResult.getEnvironment());
				// env_TestCase_TestResult.setTestCase(completeResult.getTestCase());
				// env_TestCase_TestResult.setTestResult(completeResult.getTestResult());

				return env_TestCase_TestResult;
			}

			TestSuite findedTestSuite = testSuiteService.findByName(testSuite.getName());
			
			
			testCase.setTestSuite(findedTestSuite);
			testCaseService.save(testCase);
			
			env_TestCase_TestResultService.saveWithEntities(environment, testResult, testCase);
			
			return env_TestCase_TestResult;

		}

		System.out.println("aaaaa_______+++++++++");

		environmentService.save(environment);
		testSuiteDao.save(testSuite);
		testCase.setTestSuite(testSuite);
		testCaseService.save(testCase);
		testResultService.save(testResult);

		env_TestCase_TestResult.setEnvironment(environment);
		env_TestCase_TestResult.setTestCase(testCase);
		env_TestCase_TestResult.setTestResult(testResult);

		env_TestCase_TestResultService.save(env_TestCase_TestResult);

		return env_TestCase_TestResult;
	}

}

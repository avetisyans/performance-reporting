package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.dao.Run_TestCase_TestResultDao;
import com.test.dao.EnvironmentDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Build;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.Environment;
import com.test.domain.Run;
import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.service.BuildService;
import com.test.service.Run_TestCase_TestResultService;
import com.test.service.EnvironmentService;
import com.test.service.RunService;
import com.test.service.TestCaseService;
import com.test.service.TestResultService;
import com.test.service.TestSuiteService;

@RestController
@RequestMapping("/rest")
public class TestResultRestController {
	
	@Autowired
	private Run_TestCase_TestResultService run_TestCase_TestResultService;
	
	@Autowired
	private EnvironmentService environmentService;
	
	@Autowired
	private RunService runService;
	
	@Autowired
	private TestCaseService testCaseService;
	
	@Autowired
	private TestResultService testResultService;
	
	@Autowired
	private TestSuiteService testSuiteService;
	
	@Autowired
	private Run_TestCase_TestResultDao run_TestCase_TestResultDao;
	
	@Autowired
	private EnvironmentDao environmentDao;
	
	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	private TestResultDao testResultDao;
	
	@Autowired
	private TestSuiteDao testSuiteDao;
	
	@Autowired
	private BuildService buildService;

	@RequestMapping(value = "/testJson", method = RequestMethod.POST)
	public JsonNode showTestClass(@RequestBody JsonNode envObj) {

		return envObj;
	}

	@RequestMapping(value = "/runData", method = RequestMethod.POST)
	public String showRunData(@RequestBody Run runData) {
		
		//System.out.println("runData.getBuildNumber() is: " + runData.getBuildNumber());
		if ( runData.getEndTime() == null) {
		Run savedRun = runService.saveToItsParent(runData);
		return savedRun.toString();
		}
		
		Run existingRun = runService.addEndTimeToExistingRun(runData);
		
		return runData.toString();
	}
	
/*	@RequestMapping(value = "/buildData", method = RequestMethod.POST)
	public String showBuildData(@RequestBody Build buildData) {
		
		System.out.println("buildData.getBuildNumber() is: " + buildData.getBuildNumber());
		//Build buildDB = buildService.saveToItsBranch(build);
		Build savedBuild = buildService.saveToItsBranch(buildData);
		return savedBuild.toString();
	}*/
	
	@RequestMapping(value = "/testData", method = RequestMethod.POST)
	public String showResultJson(@RequestBody Run_TestCase_TestResult run_TestCase_TestResult) {

		Run run = run_TestCase_TestResult.getRun();
		TestResult testResult = run_TestCase_TestResult.getTestResult();
		TestCase testCase = run_TestCase_TestResult.getTestCase();
		
		//List<Build> builds = environment.getBuilds();
		
		//Build build = builds.get(0);
		
		//Branch branch = builds.get(0).getBranch();
		
		//TestSuite testSuite = testCase.getTestSuite();
		
		//List<Run> runs = testSuite.getRuns();
		
		//Run run = runs.get(0);
		
		TestCase testCaseDB = testCaseService.saveToItsTestSuite(testCase);	
//		Environment environmentDB = environmentService.saveToItsBuild(environment);
		Run runDB = runService.saveToItsParent(run);
		TestResult testResultDB = testResultService.save(testResult);
		
		//Environment environmentDB = environmentService.saveToDB(environment);
		//testResultService.save(testResult);
		
//		env_TestCase_TestResult.setEnvironment(environmentDB);
		run_TestCase_TestResult.setRun(runDB);
		run_TestCase_TestResult.setTestResult(testResultDB);
		run_TestCase_TestResult.setTestCase(testCaseDB);
		
		run_TestCase_TestResultService.save(run_TestCase_TestResult);
		
		return run_TestCase_TestResult.toString();
	}
	
	@RequestMapping(value = "/run/{buildNumber}/testResults", method = RequestMethod.GET)
	public List<TestResult> getTestResultsOfRun(String builNumber) {
		
		//List<TestResult> testResults = testResultDao.findByRun(builNumber);
		
		//return testResults;
		return null;
	}

}

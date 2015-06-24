package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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


@Controller
public class TestResultController {
	
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
	
	
	@RequestMapping("/testResult/1")
	@ResponseBody
	public String createTestResult(Model model) {
		
		//List<TestResult> testresults = testResultService.findTestResultByName();
		
		//model.addAttribute("testResults", testresults);
		TestResult testResult = testResultService.findOne((long) 1);
		
		return testResult.getStartTime().toString();
	}
	
/*	@RequestMapping("/")
	public String displayHome() {
		System.out.println("aa________----+++++++++++++++++++_____------______aa");
		//List<TestResult> testresults = testResultService.findTestResultByName();
		
		//model.addAttribute("testResults", testresults);
		
		return "home";
	}*/

	
	@RequestMapping("/init")
	public String displayInit() {
		
		/*******      Save to DB           ****/
		
		TestSuite testSuite1 = new TestSuite();
		testSuite1.setName("TestSuite 1 From Controller");
		//testSuiteService.save(testSuite1);
		
		TestCase testCase1 = new TestCase();
		testCase1.setName("TestCase 1 of TestSuite 1");
		testCase1.setTestSuite(testSuite1);
		//testCaseService.save(testCase1);
		
		Environment environment = new Environment();
		//environment.setBranch("Branch1");
		//environment.setBuildNumber("1122211");
		environment.setHaEnabled(true);
		environment.setName("Environment 1");
		//environmentService.save(environment);
		
		TestResult testResult1 = new TestResult();
		//testResult1.setName("Test Result 1 From Controller");
		//testResult1.setResult("Success");
		//testResult1.setStartTime(new Timestamp(new Date().getTime()));
		//testResult1.setEndTime(new Timestamp(new Date().getTime()));
	//	testResult1.setTestType(TestType.API);
		//testResultService.save(testResult1);
		
		// Link Env, TestResult and TestCase
		
		Env_TestCase_TestResult env_TestCase_TestResult = new Env_TestCase_TestResult();
		env_TestCase_TestResult.setEnvironment(environment);
		env_TestCase_TestResult.setTestCase(testCase1);
		env_TestCase_TestResult.setTestResult(testResult1);
		env_TestCase_TestResultService.save(env_TestCase_TestResult);
		
		return "init";
	}
	
}

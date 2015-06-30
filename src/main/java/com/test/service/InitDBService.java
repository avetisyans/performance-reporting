package com.test.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.BranchDao;
import com.test.dao.BuildDao;
import com.test.dao.Run_TestCase_TestResultDao;
import com.test.dao.EnvironmentDao;
import com.test.dao.RunDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Branch;
import com.test.domain.Build;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.Environment;
import com.test.domain.Run;
import com.test.domain.TestCase;
import com.test.domain.TestCase.TestType;
import com.test.domain.TestResult;
import com.test.domain.TestResult.Result;
import com.test.domain.TestSuite;

@Service
@Transactional
public class InitDBService {

	@Autowired
	private Run_TestCase_TestResultDao run_TestCase_TestResultDao;

	@Autowired
	private EnvironmentDao environmentDao;

	@Autowired
	private BranchDao branchDao;
	
	@Autowired
	private BuildDao buildDao;
	
	@Autowired
	private TestCaseDao testCaseDao;
	
	@Autowired
	private TestResultDao testResultDao;

	@Autowired
	private RunDao runDao;

	@Autowired
	private TestSuiteDao testSuiteDao;

	//@PostConstruct
	public void init() {
	/************ First Block ***********************/
		TestSuite testSuite1 = new TestSuite();
		testSuite1.setName("testSuite1");
		testSuiteDao.save(testSuite1);
		
		Run run1 = new Run();
		run1.setBuildNumber("#111");
		run1.setTag("tag of run1");
		//run1.setStartTime(new Date());
		List<TestSuite> testSuites = new ArrayList<TestSuite>();
		testSuites.add(testSuite1);
		//run1.setTestSuites(testSuites);
		runDao.save(run1);
		
		TestCase testCase1 = new TestCase();
		testCase1.setName("testCase1");
		testCase1.setTestSuite(testSuite1);
		testCase1.setTestType(TestType.UI);
		testCaseDao.save(testCase1);
		
		TestCase testCase2 = new TestCase();
		testCase2.setName("testCase2");
		testCase2.setTestSuite(testSuite1);
		testCase2.setTestType(TestType.UI);
		testCaseDao.save(testCase2);
	/************************************************/
	
		
	/************* Second Block ********************/
		TestResult testResult1 = new TestResult();
		testResult1.setResult(Result.SUCCESS);

	//	testResult1.setStartTime(new Date());
	//	testResult1.setEndTime(new Date());
		testResultDao.save(testResult1);
		
		TestResult testResult2 = new TestResult();
		testResult2.setResult(Result.SKIPPED);

	//	testResult2.setStartTime(new Date());
	//	testResult2.setEndTime(new Date());
		testResultDao.save(testResult2);
	/***********************************************/

		
	/************* Third Block *********************/
		Branch branch1 = new Branch();
		branch1.setName("beta1");
		branchDao.save(branch1);
		
		Build build1 = new Build();
		build1.setBuildNumber("build111");
		build1.setBranch(branch1);
		buildDao.save(build1);
		
		Environment environment1 = new Environment();
		environment1.setHaEnabled(true);
		environment1.setMaster("master1");
		environment1.setName("environment1");
		environment1.setProductName("productName1");
		environment1.setDatanodes(new String[]{"FirstDataNode", "SecondDataNode", "ThirdDataNode"});
		List<Build> builds = new ArrayList<Build>();
		builds.add(build1);
		environment1.setBuilds(builds);
		environmentDao.save(environment1);
	/***********************************************/
		
	/*********** Setting All to Env_TestCase_TestResult **********/
		Run_TestCase_TestResult env_TestCase_TestResult1 = new Run_TestCase_TestResult();
		//env_TestCase_TestResult1.setEnvironment(environment1);
		env_TestCase_TestResult1.setTestCase(testCase1);
		env_TestCase_TestResult1.setTestResult(testResult1);
		run_TestCase_TestResultDao.save(env_TestCase_TestResult1);
		
		Run_TestCase_TestResult env_TestCase_TestResult2 = new Run_TestCase_TestResult();
		//env_TestCase_TestResult2.setEnvironment(environment1);
		env_TestCase_TestResult2.setTestCase(testCase2);
		env_TestCase_TestResult2.setTestResult(testResult2);
		run_TestCase_TestResultDao.save(env_TestCase_TestResult2);
	/*************************************************************/
		
	}
	
	//@PostConstruct
	public void createRun() {
		Run run = new Run();
		run.setBuildNumber("#1");
		run.setTag("tag of run1");
		//run.setStartTime(new Date());
		//List<TestSuite> testSuites = new ArrayList<TestSuite>();
		//testSuites.add(testSuite1);
		//run1.setTestSuites(testSuites);
		runDao.save(run);
	}
}

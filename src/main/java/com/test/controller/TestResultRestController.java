package com.test.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.dao.EnvironmentDao;
import com.test.dao.Run_TestCase_TestResultDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Run;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.domain.TestSuite;
import com.test.dto.ChildRunDTO;
import com.test.dto.EnvironmentDTO;
import com.test.dto.ParentRunDTO;
import com.test.dto.TestCaseWithResultDTO;
import com.test.dto.TestSuiteDTO;
import com.test.dto.TestSuiteWithResult;
import com.test.service.BuildService;
import com.test.service.EnvironmentService;
import com.test.service.RunService;
import com.test.service.Run_TestCase_TestResultService;
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
		
		return envObj.get("environment");
	}
	
	
	@RequestMapping(value = "/runs", method = RequestMethod.GET)
	public List<ParentRunDTO> getRuns() {
		
		List<Run> runsWithResults = runService.findAll();
		
		return mapToParentRunDTO(runsWithResults);
	}


	private List<ParentRunDTO> mapToParentRunDTO(List<Run> runsWithResults) {
		List<ParentRunDTO> parentRunDTOs = new ArrayList<ParentRunDTO>();
		for (Run run : runsWithResults) {
			if (run.getStartTime() == null && run.getParent() == null) {

				List<ChildRunDTO> childRunDTOs = new ArrayList<ChildRunDTO>();
				List<TestSuiteDTO> testSuiteDTOs = new ArrayList<TestSuiteDTO>();
				
				
				ParentRunDTO parentRunDTO = new ParentRunDTO();
				parentRunDTO.setBuildNumber(run.getBuildNumber());
				parentRunDTO.setId(run.getId());
				
				List<Run> childRuns = run.getChildren();
				
				for (Run childRun : childRuns) {		
						List<TestSuiteWithResult> testSuiteWithResults = new ArrayList<TestSuiteWithResult>();
						for (Run_TestCase_TestResult run_TestCase_TestResult : childRun.getRun_TestCase_TestResults()) {							
							TestCaseWithResultDTO testCaseWithResultDTO = new TestCaseWithResultDTO(run_TestCase_TestResult.getTestCase(), run_TestCase_TestResult.getTestResult());
							String testSuiteName = run_TestCase_TestResult.getTestCase().getTestSuite().getName();
							TestSuiteWithResult testSuiteWithResult = new TestSuiteWithResult(testSuiteName, testCaseWithResultDTO);
							//testSuiteDTOs.add(new TestSuiteDTO(testSuite, testCase, testResult));
							testSuiteWithResults.add(testSuiteWithResult);
						}
						
					ChildRunDTO childRunDTO = new ChildRunDTO();
					childRunDTO.setBuildNumber(childRun.getBuildNumber());
					childRunDTO.setDuration(childRun.getDuration());
					childRunDTO.setStartTime(childRun.getStartTime());
					childRunDTO.setEndTime(childRun.getEndTime());
					childRunDTO.setTag(childRun.getTag());
					childRunDTO.setId(childRun.getId());
					EnvironmentDTO envDTO = new EnvironmentDTO(childRun.getEnvironment());
					childRunDTO.setEnvironment(envDTO);
					childRunDTO.setTestSuites(TestSuiteWithResult.mapToTestSuiteDTOs(testSuiteWithResults));			
					childRunDTOs.add(childRunDTO);
				}		
				parentRunDTO.setChildren(childRunDTOs);
				parentRunDTOs.add(parentRunDTO);
			}
		}
		
		return parentRunDTOs;
	}

	@RequestMapping(value = "/runData", method = RequestMethod.POST)
	public String showRunData(@RequestBody Run runData) throws Exception {
		
		//System.out.println("runData.getBuildNumber() is: " + runData.getBuildNumber());
		if ( runData.getEndTime() == null) {
		//Run savedRun = runService.saveToItsParent(runData);
		Run createdRun = runService.createRunWithParent(runData);
		return createdRun.toString();
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
	public String showResultJson(@RequestBody Run_TestCase_TestResult run_TestCase_TestResult) throws Exception {

		Run run = run_TestCase_TestResult.getRun();
		TestResult testResult = run_TestCase_TestResult.getTestResult();
		TestCase testCase = run_TestCase_TestResult.getTestCase();
		
		//List<Build> builds = environment.getBuilds();
		
		//Build build = builds.get(0);
		
		//Branch branch = builds.get(0).getBranch();
		
		//TestSuite testSuite = testCase.getTestSuite();
		
		//List<Run> runs = testSuite.getRuns();
		
		//Run run = runs.get(0);
		Run runDB = runService.saveToItsParent(run);	
		TestCase testCaseDB = testCaseService.saveToItsTestSuite(testCase);	
//		Environment environmentDB = environmentService.saveToItsBuild(environment);

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

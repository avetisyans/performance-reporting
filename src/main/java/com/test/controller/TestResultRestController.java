package com.test.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.taglibs.standard.tag.common.core.ForEachSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.test.dao.EnvironmentDao;
import com.test.dao.Run_TestCase_TestResultDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestResultDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Environment;
import com.test.domain.Run;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;
import com.test.domain.TestResult;
import com.test.domain.TestResult.Result;
import com.test.dto.ChildRunDTO;
import com.test.dto.DurationsByDateWrapper;
import com.test.dto.EnvironmentDTO;
import com.test.dto.EnvironmentWithStatisticsDTO;
import com.test.dto.EnvironmentsDTO;
import com.test.dto.ParentRunDTO;
import com.test.dto.TestCaseWithResultDTO;
import com.test.dto.TestCaseWithStatisticsDTO;
import com.test.dto.TestCaseDTO;
import com.test.dto.TestResultsByDateDTO;
import com.test.dto.TestResultsByDateWrapperDTO;
import com.test.dto.TestSuiteWithResultDTO;
import com.test.dto.TestSuiteWithResultEntity;
import com.test.dto.TestSuiteWithStatisticsDTOWrapper;
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
	
	private Long getCurrentTimeBefore(int numberOfDays) {
		Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -numberOfDays);
	    cal.set(Calendar.HOUR_OF_DAY,0);
	    cal.set(Calendar.MINUTE,0);
	    cal.set(Calendar.SECOND,0);
	    cal.set(Calendar.MILLISECOND,0);
	    
	    return cal.getTime().getTime();
	}

	@RequestMapping(value = "/testJson", method = RequestMethod.POST)
	public JsonNode showTestClass(@RequestBody JsonNode envObj) {
		
		return envObj.get("environment");
	}
	
	
	@RequestMapping(value = "/runs", method = RequestMethod.GET)
	public List<ParentRunDTO> getRuns(@RequestParam(value="numberOfParentRuns", required=false) Integer numberOfParentRuns) {
		
		System.out.println("number of parent runs: " + numberOfParentRuns);
		
		int parentRuns = (numberOfParentRuns == null) ? 3 : numberOfParentRuns;
		//Page<Run> runsWithResults = runService.findParents(new PageRequest(0, 5));
		// List<Run> runsWithResults = runService.findAll();
		List<Run> runsWithResults = runService.findParentRuns(new PageRequest(0, parentRuns));
		
/*		System.out.println("ParentRuns: " + runsWithResults);
		for (Run run : runsWithResults) {
			List<Run> children = run.getChildren();
			System.out.println("children: " + children);
		}*/
		
		return mapToParentRunDTO(runsWithResults);
	}
	
	private List<ParentRunDTO> mapToParentRunDTO(List<Run> runsWithResults) {
		List<ParentRunDTO> parentRunDTOs = new ArrayList<ParentRunDTO>();
		for (Run run : runsWithResults) {
			//if (run.getEndTime() == null && run.getParent() == null) {

				List<ChildRunDTO> childRunDTOs = new ArrayList<ChildRunDTO>();
				List<TestSuiteWithResultDTO> testSuiteWithResultDTOs = new ArrayList<TestSuiteWithResultDTO>();
				
				
				ParentRunDTO parentRunDTO = new ParentRunDTO();
				parentRunDTO.setBuildNumber(run.getBuildNumber());
				parentRunDTO.setId(run.getId());
				
				List<Run> childRuns = run.getChildren();
				
				for (Run childRun : childRuns) {		
						List<TestSuiteWithResultEntity> testSuiteWithResultEntities = new ArrayList<TestSuiteWithResultEntity>();
						for (Run_TestCase_TestResult run_TestCase_TestResult : childRun.getRun_TestCase_TestResults()) {							
							TestCaseWithResultDTO testCaseWithResultDTO = new TestCaseWithResultDTO(run_TestCase_TestResult.getTestCase(), run_TestCase_TestResult.getTestResult());
							String testSuiteName = run_TestCase_TestResult.getTestCase().getTestSuite().getName();
							TestSuiteWithResultEntity testSuiteWithResultEntity = new TestSuiteWithResultEntity(testSuiteName, testCaseWithResultDTO);
							//testSuiteDTOs.add(new TestSuiteDTO(testSuite, testCase, testResult));
							testSuiteWithResultEntities.add(testSuiteWithResultEntity);
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
					childRunDTO.setTestSuites(TestSuiteWithResultEntity.mapToTestSuiteDTOs(testSuiteWithResultEntities));			
					childRunDTOs.add(childRunDTO);
				}		
				parentRunDTO.setChildren(childRunDTOs);
				parentRunDTOs.add(parentRunDTO);
			//}
		}
		
		return parentRunDTOs;
	}
	
	@RequestMapping(value = "/allEnvironments", method = RequestMethod.GET)
	public List<EnvironmentsDTO> getAllEnvs() {
		List<Environment> envs = environmentService.findAll();
		List<EnvironmentsDTO> envsDTOs = new ArrayList<EnvironmentsDTO>();
		for (Environment environment : envs) {
			EnvironmentsDTO environmentsDTO = new EnvironmentsDTO();
			environmentsDTO.setId(environment.getId());
			environmentsDTO.setName(environment.getName());
			envsDTOs.add(environmentsDTO);
		}
		return envsDTOs;
	}
	
	@RequestMapping(value = "/testCases", method = RequestMethod.GET)
	public List<TestCaseDTO> getTestCases() {
		//List<Environment> envs = environmentService.findAll();
		List<TestCase> testCases = testCaseService.findAll();
		List<TestCaseDTO> testcasesDTOs = new ArrayList<TestCaseDTO>();
		for (TestCase testCase : testCases) {
			TestCaseDTO testCaseDTO = new TestCaseDTO();
			testCaseDTO.setId(testCase.getId());
			testCaseDTO.setName(testCase.getName());
			testcasesDTOs.add(testCaseDTO);
		}
		return testcasesDTOs;
	}
	
	
	
	@RequestMapping(value = "/testResults", method = RequestMethod.GET)
	public TestResultsByDateWrapperDTO getTestResults(@RequestParam(value="testCaseId") Long testCaseId, @RequestParam(value="envIds") String[] envIds, @RequestParam(value="numberOfRecentDays") int numberOfRecentDays ) {
		
		//List<TestResult> testResults = testResultService.findByEnvAndTestCase(1L, 1L, new PageRequest(0, 3));
		TestResultsByDateWrapperDTO testResultsByDateWrapperDTO = new TestResultsByDateWrapperDTO();
		List<TestResultsByDateDTO> trDTOs = new ArrayList<TestResultsByDateDTO>();
		TestCase tc = testCaseService.findOne(testCaseId);
		for (String envId : envIds) {
			long parsedEnvId = Long.parseLong(envId);
			Environment env = environmentService.findOne(parsedEnvId);
			
			List<TestResult> testResults = testResultService.findByEnvAndTestCaseForRecentDays(testCaseId, parsedEnvId, getCurrentTimeBefore(numberOfRecentDays));
			
			DurationsByDateWrapper durationsByDateWrapper = new DurationsByDateWrapper();
			for (TestResult testResult : testResults) {
				durationsByDateWrapper.addDurationsByDate(testResult);
			}
			
			TestResultsByDateDTO testResultsByDateDTO = new TestResultsByDateDTO();
			testResultsByDateDTO.setEnvironmentName(env.getName());
			testResultsByDateDTO.setTestCaseName(tc.getName());
			testResultsByDateDTO.addDurationsToStatistics(durationsByDateWrapper);
			
			System.out.println("testResultsByDateDTO: " + testResultsByDateDTO);
			trDTOs.add(testResultsByDateDTO);
		}
		testResultsByDateWrapperDTO.setChartData(trDTOs);
		
		return testResultsByDateWrapperDTO;
	}
	
	@RequestMapping(value = "/testRuns", method = RequestMethod.GET)
	public List<Run> getTestRuns() {
		
		List<Run> testRuns = runService.findByEnvAndTestCase(1L,2L, new PageRequest(0, 10));
		
		for (Run run : testRuns) {
			List<Run_TestCase_TestResult> run_TestCase_TestResults = run.getRun_TestCase_TestResults();
			for (Run_TestCase_TestResult run_TestCase_TestResult : run_TestCase_TestResults) {
				run_TestCase_TestResult.getTestResult();
			}
		}
		
		System.out.println("Before Return");
		
		return testRuns;
	}
	
	@RequestMapping(value = "/environments", method = RequestMethod.GET)
	public List<EnvironmentWithStatisticsDTO> getEnvs(@RequestParam(required = false, value="numberOfRecentRuns") Integer numberOfRecentRuns) {
		
/*		long startTime = 0;
		long endTime = 0;*/
		
		List<EnvironmentWithStatisticsDTO> environmentWithStatisticsDTOs = new ArrayList<EnvironmentWithStatisticsDTO>();
		
		List<Environment> environments = environmentService.findAll();
		List<TestCase> testCases = testCaseService.findAll();
		

		int recentRuns = (numberOfRecentRuns == null) ? 2 : numberOfRecentRuns;

		for (Environment environment : environments) {
			
			List<Run_TestCase_TestResult> run_TestCase_TestResultsByEnv = run_TestCase_TestResultService.findByEnvironment(environment.getId(), new PageRequest(0, 100));
			
			TestSuiteWithStatisticsDTOWrapper testSuiteWithStatisticsDTOWrapper = new TestSuiteWithStatisticsDTOWrapper();
			
			for (TestCase testCase : testCases) {
				
				//List<Run> runs = runService.findByEnvAndTestCase(environment.getId(), testCase.getId(), new PageRequest(0, recentRuns));
				List<Run> allRuns = new ArrayList<Run>();
				List<Run> runs = null;
				for (Run_TestCase_TestResult run_TestCase_TestResult : run_TestCase_TestResultsByEnv) {
					if (run_TestCase_TestResult.getTestCase().getName() == testCase.getName()) {
						allRuns.add(run_TestCase_TestResult.getRun());
					}
				}
				
				if (allRuns.size() >= recentRuns) {
						runs = allRuns.subList(0, recentRuns);
				}
				
				
				List<Long> durations = new ArrayList<Long>();
				long totalRuns = recentRuns;
				if (runs != null) {
					for (Run run : runs) {
						Run_TestCase_TestResult run_TestCase_TestResult = new Run_TestCase_TestResult();
/*						List<Run_TestCase_TestResult> run_TestCase_TestResults = run.getRun_TestCase_TestResults();*/
						//Run_TestCase_TestResult run_TestCase_TestResult = run_TestCase_TestResultService.findByRunAndTestCase(run, testCase);
						List<Run_TestCase_TestResult> run_TestCase_TestResults = run.getRun_TestCase_TestResults();
						for (Run_TestCase_TestResult run_TestCase_TestResult2 : run_TestCase_TestResults) {
							if (run_TestCase_TestResult2.getTestCase().getName() == testCase.getName()) {
								run_TestCase_TestResult = run_TestCase_TestResult2;
								break;
							}
						}
						
							long duration = run_TestCase_TestResult.getTestResult().getEndTime() - run_TestCase_TestResult.getTestResult().getStartTime();
							
							if(run_TestCase_TestResult.getTestResult().getResult() == Result.SUCCESS) {
								durations.add(duration);
							}

					}		
					
					
						TestCaseWithStatisticsDTO testCaseWithStatisticsDTO = new TestCaseWithStatisticsDTO(testCase.getTestSuite().getName(),testCase.getName(), durations, totalRuns);
						testSuiteWithStatisticsDTOWrapper.addTestCaseWithStatisticsDTO(testCaseWithStatisticsDTO);
						
						

				}
			}

/*			startTime = System.currentTimeMillis();*/
			
			EnvironmentWithStatisticsDTO environmentWithStatisticsDTO = new EnvironmentWithStatisticsDTO(environment.getName(), testSuiteWithStatisticsDTOWrapper.getTestSuiteWithStatisticsDTOs());
			environmentWithStatisticsDTOs.add(environmentWithStatisticsDTO);
			
/*			endTime = System.currentTimeMillis();
			System.out.println("That took " + (endTime - startTime) + " milliseconds");
			System.out.println("Finished Timing");*/
		}
		

		
		return environmentWithStatisticsDTOs;
	}
	

	
/*	@RequestMapping(value = "/environments", method = RequestMethod.GET)
	public List<EnvironmentWithStatisticsDTO> getEnvs(@RequestParam(required = false, value="numberOfRecentRuns") Integer numberOfRecentRuns) {
		
		long startTime = 0;
		long endTime = 0;
		
		List<EnvironmentWithStatisticsDTO> environmentWithStatisticsDTOs = new ArrayList<EnvironmentWithStatisticsDTO>();
		
		List<Environment> environments = environmentService.findAll();
		List<TestCase> testCases = testCaseService.findAll();
		

		int recentRuns = (numberOfRecentRuns == null) ? 2 : numberOfRecentRuns;

		for (Environment environment : environments) {
			TestSuiteWithStatisticsDTOWrapper testSuiteWithStatisticsDTOWrapper = new TestSuiteWithStatisticsDTOWrapper();
			
			for (TestCase testCase : testCases) {
				
				List<Run> runs = runService.findByEnvAndTestCase(environment.getId(), testCase.getId(), new PageRequest(0, recentRuns));
				

				
				List<Long> durations = new ArrayList<Long>();
				long totalRuns = recentRuns;
				if (runs.size() == recentRuns) {
					for (Run run : runs) {
						Run_TestCase_TestResult run_TestCase_TestResult = new Run_TestCase_TestResult();
						List<Run_TestCase_TestResult> run_TestCase_TestResults = run.getRun_TestCase_TestResults();
						//Run_TestCase_TestResult run_TestCase_TestResult = run_TestCase_TestResultService.findByRunAndTestCase(run, testCase);
						List<Run_TestCase_TestResult> run_TestCase_TestResults = run.getRun_TestCase_TestResults();
						for (Run_TestCase_TestResult run_TestCase_TestResult2 : run_TestCase_TestResults) {
							if (run_TestCase_TestResult2.getTestCase().getName() == testCase.getName()) {
								run_TestCase_TestResult = run_TestCase_TestResult2;
								break;
							}
						}
						
							long duration = run_TestCase_TestResult.getTestResult().getEndTime() - run_TestCase_TestResult.getTestResult().getStartTime();
							
							if(run_TestCase_TestResult.getTestResult().getResult() == Result.SUCCESS) {
								durations.add(duration);
							}

					}		
					
					
						TestCaseWithStatisticsDTO testCaseWithStatisticsDTO = new TestCaseWithStatisticsDTO(testCase.getTestSuite().getName(),testCase.getName(), durations, totalRuns);
						testSuiteWithStatisticsDTOWrapper.addTestCaseWithStatisticsDTO(testCaseWithStatisticsDTO);
						
						

				}
			}

			startTime = System.currentTimeMillis();
			
			EnvironmentWithStatisticsDTO environmentWithStatisticsDTO = new EnvironmentWithStatisticsDTO(environment.getName(), testSuiteWithStatisticsDTOWrapper.getTestSuiteWithStatisticsDTOs());
			environmentWithStatisticsDTOs.add(environmentWithStatisticsDTO);
			
			endTime = System.currentTimeMillis();
			System.out.println("That took " + (endTime - startTime) + " milliseconds");
			System.out.println("Finished Timing");
		}
		

		
		return environmentWithStatisticsDTOs;
	}*/
	
/*	@RequestMapping(value = "/environments", method = RequestMethod.GET)
	public List<EnvironmentWithStatisticsDTO> getEnvironments() {
		
		List<Environment> environments = environmentService.findAll();
		
		return mapToEnvironmentDTOWithSuites(environments);
	}*/


/*	private List<EnvironmentWithResultDTO> mapToEnvironmentDTO(List<Environment> environments) {
		
		List<EnvironmentWithResultDTO> envWithResults = new ArrayList<EnvironmentWithResultDTO>();
		
		for (Environment environment : environments) {
			EnvironmentWithResultDTO environmentWithResultDTO = new EnvironmentWithResultDTO();
			environmentWithResultDTO.setName(environment.getName());
			List<TestCaseWithStatisticsDTO> testCaseWithStatisticsDTO = new ArrayList<TestCaseWithStatisticsDTO>();
			
			
			List<TestCase> uniqueTestCases = getUniqueTestCases(environment);
			
			for (TestCase testCase : uniqueTestCases) {
				TestCaseWithStatisticsDTO testCaseWithStatistic = new TestCaseWithStatisticsDTO();
				List<Run_TestCase_TestResult> run_TestCase_TestResults = testCase.getRun_TestCase_TestResults();
				//testCaseWithStatistic.setTotalRuns((long) run_TestCase_TestResults.size());
				
				List<Long> durations = new ArrayList<Long>();
				long numberOfFailures = 0;
				long totalRuns = 0;
				for (Run_TestCase_TestResult run_TestCase_TestResult : run_TestCase_TestResults) {
					long duration = run_TestCase_TestResult.getRun().getEndTime() - run_TestCase_TestResult.getRun().getStartTime();
					totalRuns = (long) run_TestCase_TestResults.size();
					
					if(run_TestCase_TestResult.getTestResult().getResult() == Result.SUCCESS) {
						durations.add(duration);
					} else {
						++numberOfFailures;
					}
					
				}
				long sum = 0;
				for (Long long1 : durations) {
					sum += long1;
				}
				
				long numberOfPassed = durations.size();
				long avg = sum/numberOfPassed;
				long max = Collections.max(durations);
				long min = Collections.min(durations);
				
				testCaseWithStatistic.setName(testCase.getName());
				testCaseWithStatistic.setTotalRuns(totalRuns);
				testCaseWithStatistic.setPassed(numberOfPassed);
				testCaseWithStatistic.setFailed(numberOfFailures);
				testCaseWithStatistic.setSuccessfulMin(min);
				testCaseWithStatistic.setSuccessfulMax(max);
				testCaseWithStatistic.setSuccessfulAvg(avg);
				testCaseWithStatisticsDTO.add(testCaseWithStatistic);
				//testCaseWithStatistic.set
			}
			
			environmentWithResultDTO.setTestCaseData(testCaseWithStatisticsDTO);
			envWithResults.add(environmentWithResultDTO);
		}

		
		return envWithResults;
	}*/
/*	private List<EnvironmentWithStatisticsDTO> mapToEnvironmentDTOWithSuites(List<Environment> environments) {
		
		List<EnvironmentWithStatisticsDTO> envWithResults = new ArrayList<EnvironmentWithStatisticsDTO>();
		
		for (Environment environment : environments) {
			EnvironmentWithStatisticsDTO environmentWithStatisticsDTO = new EnvironmentWithStatisticsDTO();
			environmentWithStatisticsDTO.setName(environment.getName());
			List<TestSuiteWithStatisticsDTO> testSuiteWithStatisticsDTO = new ArrayList<TestSuiteWithStatisticsDTO>();
			List<TestCaseWithStatisticsDTO> testCaseWithStatisticsDTOs = new ArrayList<TestCaseWithStatisticsDTO>();
			
			List<TestSuiteWithStatisticsEntity> testSuiteWithStatisticsEntities = new ArrayList<TestSuiteWithStatisticsEntity>();
			
			List<TestCase> uniqueTestCases = getUniqueTestCases(environment);
			
			for (TestCase testCase : uniqueTestCases) {
				TestCaseWithStatisticsDTO testCaseWithStatistic = new TestCaseWithStatisticsDTO();
				List<Run_TestCase_TestResult> run_TestCase_TestResults = testCase.getRun_TestCase_TestResults();
				//testCaseWithStatistic.setTotalRuns((long) run_TestCase_TestResults.size());
				
				List<Long> durations = new ArrayList<Long>();
				long numberOfFailures = 0;
				long totalRuns = 0;
				for (Run_TestCase_TestResult run_TestCase_TestResult : run_TestCase_TestResults) {
					long duration = run_TestCase_TestResult.getTestResult().getEndTime() - run_TestCase_TestResult.getTestResult().getStartTime();
					totalRuns = (long) run_TestCase_TestResults.size();
					
					if(run_TestCase_TestResult.getTestResult().getResult() == Result.SUCCESS) {
						durations.add(duration);
					} else {
						++numberOfFailures;
					}
					
					String testSuiteName = run_TestCase_TestResult.getTestCase().getTestSuite().getName();
					TestSuiteWithStatisticsEntity testSuiteWithStatisticsEntity = new TestSuiteWithStatisticsEntity(testSuiteName, testCaseWithStatisticsDTO);
					
				}
				long sum = 0;
				for (Long long1 : durations) {
					sum += long1;
				}
				
				long numberOfPassed = durations.size();
				long avg = sum/numberOfPassed;
				long max = Collections.max(durations);
				long min = Collections.min(durations);
				
				testCaseWithStatistic.setName(testCase.getName());
				testCaseWithStatistic.setTotalRuns(totalRuns);
				testCaseWithStatistic.setPassed(numberOfPassed);
				testCaseWithStatistic.setFailed(numberOfFailures);
				testCaseWithStatistic.setSuccessfulMin(min);
				testCaseWithStatistic.setSuccessfulMax(max);
				testCaseWithStatistic.setSuccessfulAvg(avg);
				
				String testSuiteName = testCase.getTestSuite().getName();
				TestSuiteWithStatisticsEntity testSuiteWithStatisticsEntity = new TestSuiteWithStatisticsEntity(testSuiteName, testCaseWithStatistic);
				testSuiteWithStatisticsEntities.add(testSuiteWithStatisticsEntity);
				//testCaseWithStatisticsDTO.add(testCaseWithStatistic);
				//testCaseWithStatistic.set
			}
			
			//testSuiteWithStatisticsDTO.setTestCaseData(testCaseWithStatisticsDTO);
			//envWithResults.add(environmentWithResultDTO);
			environmentWithStatisticsDTO.setTestSuites(TestSuiteWithStatisticsEntity.mapToTestSuiteWithStatisticsDTOs(testSuiteWithStatisticsEntities));
			envWithResults.add(environmentWithStatisticsDTO);
		}
		
		
		return envWithResults;
	}*/


	private List<TestCase> getUniqueTestCases(Environment environment) {
		Set<TestCase> testCasesSet = new HashSet<TestCase>();
		
		
		List<Run> runs = environment.getRuns();
		
		for (Run run : runs) {
			List<Run_TestCase_TestResult> run_TestCase_TestResults = run.getRun_TestCase_TestResults();
			for (Run_TestCase_TestResult run_TestCase_TestResult : run_TestCase_TestResults) {
				testCasesSet.add(run_TestCase_TestResult.getTestCase());
			}
		}
		
		List<TestCase> testCases = new ArrayList<TestCase>();
		
		testCases.addAll(testCasesSet);
		
		return testCases;
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

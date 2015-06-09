/*package com.vmware.vrops.performance.reporting.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;
import com.vmware.vrops.performance.reporting.domain.TestRunInfo;
import com.vmware.vrops.performance.reporting.domain.TestSuiteListPerEnv;
import com.vmware.vrops.performance.reporting.service.DailyRunInfoService;
import com.vmware.vrops.performance.reporting.service.EnvironmentConfigService;
import com.vmware.vrops.performance.reporting.service.TestDefService;
import com.vmware.vrops.performance.reporting.service.TestListRunInfoService;
import com.vmware.vrops.performance.reporting.service.TestRunInfoService;
import com.vmware.vrops.performance.reporting.service.TestSuiteService;

@RestController
@RequestMapping("/rest")
public class TestsRestController {

	@Autowired
	private DailyRunInfoService dailyRunInfoService;
	@Autowired
	private TestRunInfoService testRunInfoService;
	@Autowired
	private TestListRunInfoService testListRunInfoService;
	@Autowired
	private TestSuiteService testSuiteService;
	@Autowired
	private TestDefService testDefService;
	@Autowired
	private EnvironmentConfigService environmentConfigService;

	@RequestMapping(value = "/environmentConfigs", method = RequestMethod.GET)
	public List<EnvironmentConfig> getEnvironmentConfigs() {
		List<EnvironmentConfig> envConfigs = environmentConfigService.findEnvironmentConfigs();

		for(EnvironmentConfig envConfig: envConfigs){
			TestSuiteListPerEnv testSuiteListPerEnv = envConfig.getTestSuiteListPerEnv();
			testSuiteListPerEnv.setDailyRunInfo(null);
		}
		return envConfigs;
	}

	@RequestMapping(value = "/dailyrun", method = RequestMethod.GET)
	public List<DailyRunInfo> getDailyRunInfos(@RequestParam(value="date") String date){
		List<DailyRunInfo> dailyRunInfos = dailyRunInfoService.findByDate(date);

		for(DailyRunInfo dailyRunInfo: dailyRunInfos){
			Set<TestSuiteListPerEnv> testSuiteListPerEnvs = dailyRunInfo.getTestSuitesPerEnv();
			for(TestSuiteListPerEnv testSuiteListPerEnv: testSuiteListPerEnvs){
				testSuiteListPerEnv.setDailyRunInfo(null);
				testSuiteListPerEnv.setTestSuites(null);
				testSuiteListPerEnv.getEnvironmentConfig().setTestSuiteListPerEnv(null);
			}
		}
		return dailyRunInfos;
	}

	@RequestMapping(value = "/dailyrun", method = RequestMethod.POST)
	public void saveDailyRunInfo(@RequestBody DailyRunInfo dailyRunInfo){
		dailyRunInfoService.save(dailyRunInfo);
	}
	
	@RequestMapping(value= "/testrun", method = RequestMethod.POST) 
	public void saveTestRunInfo(@RequestBody TestRunInfo testRunInfo) {
		testRunInfoService.save(testRunInfo);
	}
	

}*/
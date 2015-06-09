/*package com.vmware.vrops.performance.reporting.controller;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;
import com.vmware.vrops.performance.reporting.domain.TestDef;
import com.vmware.vrops.performance.reporting.domain.TestListRunInfo;
import com.vmware.vrops.performance.reporting.domain.TestRunInfo;
import com.vmware.vrops.performance.reporting.domain.TestSuiteListPerEnv;
import com.vmware.vrops.performance.reporting.service.DailyRunInfoService;
import com.vmware.vrops.performance.reporting.service.EnvironmentConfigService;
import com.vmware.vrops.performance.reporting.service.TestDefService;
import com.vmware.vrops.performance.reporting.service.TestListRunInfoService;
import com.vmware.vrops.performance.reporting.service.TestRunInfoService;
import com.vmware.vrops.performance.reporting.service.TestSuiteService;

@Controller
public class TestsController {

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

	@RequestMapping(value = { "/", "TestSuites" }, method = RequestMethod.GET)
	public ModelAndView showSuites() {
		ModelAndView model = new ModelAndView();
		model.setViewName("TestSuites");
		List<DailyRunInfo> dailyRunInfosAll = dailyRunInfoService.findAll();
		model.addObject("dailyRunInfosAll", dailyRunInfosAll);
		// TODO get dailyRunInfos by Latest Date
		List<DailyRunInfo> dailyRunInfos = dailyRunInfosAll;
		if (!dailyRunInfos.isEmpty()) {
			DailyRunInfo dailyRunInfo = dailyRunInfos.get(0);
			Set<TestSuiteListPerEnv> testSuiteListPerEnvs = dailyRunInfo
					.getTestSuitesPerEnv();
			model.addObject("testSuiteListPerEnvs", testSuiteListPerEnvs);

		}
		// get all environments
		List<String> environmentConfigNames = environmentConfigService
				.findEnvironmentNames();
		model.addObject("environmentConfigNames", environmentConfigNames);

		List<String> testSuiteNames = testSuiteService.findSuiteNames();
		model.addObject("testSuiteNames", testSuiteNames);

		return model;
	}

	@RequestMapping(value = "/TestSuites/{whatToShow}", method = RequestMethod.POST)
	public @ResponseBody
	PartialViewModel showTestCases(@PathVariable String whatToShow,
			HttpServletRequest request) {
		if (whatToShow.equals("tests")) {
			Enumeration keys = request.getParameterNames();
			HashMap<String, List<TestListRunInfo>> map = new HashMap<String, List<TestListRunInfo>>();
			String key = null;
			while (keys.hasMoreElements()) {
				key = keys.nextElement().toString();
				List<TestListRunInfo> testRunInfos = testListRunInfoService
						.findBySuiteId(key);
				// delete all back references to avoid stack overflow, find
				// better way
				for (TestListRunInfo testListRunInfo : testRunInfos) {
					testListRunInfo.setTestSuite(null);
					testListRunInfo
							.setTestRunInfos(new LinkedHashSet<TestRunInfo>());
				}
				map.put(request.getParameter(key), testRunInfos);
			}
			PartialViewModel partialViewModel = new PartialViewModel();
			partialViewModel.setMap(map);
			List<TestDef> testDefs = testDefService.findAll();
			partialViewModel.setTestDef(testDefs);
			return partialViewModel;
		} else {
			// TODO
			return null;
		}
	}

	// now result for all days, must be able to specify date range
	@RequestMapping(value = "/TestSuites/history", method = RequestMethod.POST)
	public @ResponseBody
	List<TestListRunInfo> showHistory(HttpServletRequest request) {
		// TODO add
		String startDate = null;
		String endDate = null;
		Enumeration keys = request.getParameterNames();
		String testName = request.getParameter( keys.nextElement().toString());
		String suiteName = request.getParameter (keys.nextElement().toString());
		// TODO get all above parameters from request(request contains only
		// testName and  suiteName, startDate and endDate must be added from )
		List history = dailyRunInfoService.findHistory(startDate, endDate,
				suiteName, testName);
		return history;
	}

	@RequestMapping(value = "/TestSuites/testDailyRuns", method = RequestMethod.POST)
	public @ResponseBody
	List<EnvironmentConfig> showTestCases(HttpServletRequest request) {
		Enumeration keys = request.getParameterNames();
		String environmentName = keys.nextElement().toString();
		List<EnvironmentConfig> environmentConfigs = environmentConfigService
				.findEnvironmentConfigsByName(environmentName);
		// delete all back references to avoid stack overflow, find better
		// way
		for (EnvironmentConfig environmentConfig : environmentConfigs) {
			TestSuiteListPerEnv testSuiteListPerEnv = environmentConfig
					.getTestSuiteListPerEnv();
			testSuiteListPerEnv.setEnvironmentConfig(null);
			testSuiteListPerEnv.setTestSuites(null);
			testSuiteListPerEnv.getDailyRunInfo().setTestSuitesPerEnv(null);
		}
		return environmentConfigs;
	}

	@RequestMapping(value = "/TestSuites/test", method = RequestMethod.POST)
	public @ResponseBody
	Map<String, List<TestRunInfo>> showTestInfo(HttpServletRequest request) {
		String testDefName = request.getParameterNames().nextElement()
				.toString();

		List<String> environmentConfigNames = environmentConfigService
				.findEnvironmentNames();
		Map<String, List<TestRunInfo>> map = new HashMap<String, List<TestRunInfo>>();
		for (String environmentName : environmentConfigNames) {
			// TODO use ids instead of names
			List<TestRunInfo> testRunInfoList = testRunInfoService
					.findByTestDefNameAndEnv(testDefName, environmentName);
			// delete all back references to avoid stack overflow, find better
			// way
			for (TestRunInfo testRunInfo : testRunInfoList) {
				testRunInfo.setTestListRunInfo(null);
				testRunInfo.setTestDef(null);
				testRunInfo.getEnvironmentConfig().setTestSuiteListPerEnv(null);
			}
			map.put(environmentName, testRunInfoList);
		}
		return map;

	}

	public class PartialViewModel {
		public List<TestDef> getTestDef() {
			return testDef;
		}

		PartialViewModel() {
		};

		public void setTestDef(List<TestDef> testDef) {
			this.testDef = testDef;
		}

		public HashMap<String, List<TestListRunInfo>> getMap() {
			return map;
		}

		public void setMap(HashMap<String, List<TestListRunInfo>> map) {
			this.map = map;
		}

		public List<TestDef> testDef;
		public HashMap<String, List<TestListRunInfo>> map;
	}
}
*/
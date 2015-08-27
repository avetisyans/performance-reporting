package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.Run_TestCase_TestResultDao;
import com.test.dao.TestCaseDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;
import com.test.domain.TestSuite;
import com.test.service.TestCaseService;
import com.test.service.TestSuiteService;

@Service("testCaseService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

	@Autowired
	private TestCaseDao testCaseDao;

	@Autowired
	private TestSuiteDao testSuiteDao;
	
	@Autowired
	private TestSuiteService testSuiteService;

	@Autowired
	private Run_TestCase_TestResultDao run_TestCase_TestResultDao;

	@Override
	public List<TestCase> findAll() {
		return testCaseDao.findAll();
	}

	@Override
	public TestCase save(TestCase testCase) {
		return testCaseDao.save(testCase);
	}

	@Override
	public TestCase findByName(String name) {
		return testCaseDao.findByName(name);
	}

	@Override
	public TestCase saveToItsTestSuite(TestCase testCase) {
		TestSuite testSuiteFromTestCase = testCase.getTestSuite();
		
		TestSuite savedTestSuite = testSuiteService.saveToDB(testSuiteFromTestCase);
		
		
		
/*		TestCase testCaseFromDB = testCaseDao.findByName(testCase.getName());

		if (testCaseFromDB != null) {
			//testCaseFromDB.getTestSuite().getRuns().get(0).getBuildNumber();
			return testCaseFromDB;

		} else {

			TestSuite testSuite = testCase.getTestSuite();
			
			TestSuite savedTestSuite = testSuiteService.saveToItsRun(testSuite);

			testCase.setTestSuite(savedTestSuite);

			return testCaseDao.save(testCase);
		}*/
		
		TestCase testCaseByName = testCaseDao.findByName(testCase.getName());
		
		if (testCaseByName != null) {
			testCaseByName.setTestSuite(savedTestSuite);
			return testCaseByName;
		} else {
			testCase.setTestSuite(savedTestSuite);
			return testCaseDao.save(testCase);
		}
	}

	@Override
	public TestCase findOneWithResults(long id) {
		TestCase testCase = testCaseDao.findOne(id);

		List<Run_TestCase_TestResult> run_TestCase_TestResults = run_TestCase_TestResultDao.findByTestCase(testCase);
		testCase.setRun_TestCase_TestResults(run_TestCase_TestResults);

		return testCase;
	}

	@Override
	public TestCase findOne(Long testCaseId) {
		return testCaseDao.findOne(testCaseId);
	}

	/*
	 * public Car saveToItsGarage(Car car1) { Garage garage = car1.getGarage();
	 * Garage garage2 = garageDao.findByName(garage.getName());
	 * 
	 * if (garage2 == null) { garageDao.save(garage); return carDao.save(car1);
	 * }
	 * 
	 * car1.setGarage(garage2); return carDao.save(car1);
	 * 
	 * }
	 */

}

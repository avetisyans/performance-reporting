package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.TestCaseDao;
import com.test.dao.TestSuiteDao;
import com.test.domain.TestCase;
import com.test.domain.TestSuite;
import com.test.service.TestCaseService;

@Service("testCaseService")
@Transactional
public class TestCaseServiceImpl implements TestCaseService {

	@Autowired
	private TestCaseDao testCaseDao;

	@Autowired
	private TestSuiteDao testSuiteDao;

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
		TestCase testCaseFromDB = testCaseDao.findByName(testCase.getName());

		if (testCaseFromDB != null) {
			
			return testCaseFromDB;
			
		} else {

			TestSuite testSuite = testCase.getTestSuite();
			TestSuite testSuiteFromDB = testSuiteDao.findByName(testSuite.getName());

			if (testSuiteFromDB == null) {
				testSuiteDao.save(testSuite);
				return testCaseDao.save(testCase);
			}

			testCase.setTestSuite(testSuiteFromDB);

			return testCaseDao.save(testCase);
		}
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

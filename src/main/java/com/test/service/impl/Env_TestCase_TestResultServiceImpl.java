package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.Env_TestCase_TestResultDao;
import com.test.domain.Env_TestCase_TestResult;
import com.test.service.Env_TestCase_TestResultService;

@Service("env_TestCase_TestResultService")
@Transactional
public class Env_TestCase_TestResultServiceImpl implements Env_TestCase_TestResultService {

	@Autowired
	private Env_TestCase_TestResultDao env_TestCase_TestResultDao;
	
	@Override
	public List<Env_TestCase_TestResult> findAll() {
		return env_TestCase_TestResultDao.findAll();
	}

	@Override
	public Env_TestCase_TestResult save(Env_TestCase_TestResult env_TestCase_TestResult) {
		return env_TestCase_TestResultDao.save(env_TestCase_TestResult);
	}

}


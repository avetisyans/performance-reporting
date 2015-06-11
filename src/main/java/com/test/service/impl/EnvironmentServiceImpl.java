package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.EnvironmentDao;
import com.test.domain.Environment;
import com.test.service.EnvironmentService;

@Service("environmentService")
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private EnvironmentDao environmentDao;
	
	@Override
	public List<Environment> findAll() {
		return environmentDao.findAll();
	}

	@Override
	public Environment save(Environment environment) {
		return environmentDao.save(environment);
	}

	@Override
	public Environment saveToDB(Environment environment) {
		Environment environmentFromDB = environmentDao.findByBuildNumberAndMaster(environment.getBuildNumber(), environment.getMaster());
		
		if (environmentFromDB == null) {
			return environmentDao.save(environment);
		}
		
		return environmentFromDB;
	}

}

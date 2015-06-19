package com.test.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.EnvironmentDao;
import com.test.domain.Build;
import com.test.domain.Environment;
import com.test.service.BuildService;
import com.test.service.EnvironmentService;

@Service("environmentService")
@Transactional
public class EnvironmentServiceImpl implements EnvironmentService {

	@Autowired
	private EnvironmentDao environmentDao;
	
	@Autowired
	private BuildService buildService;
	
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
		Environment environmentFromDB = environmentDao.findByNameAndMaster(environment.getName(), environment.getMaster());
		
		if (environmentFromDB == null) {
			return environmentDao.save(environment);
		}
		
		return environmentFromDB;
	}

	@Override
	public Environment saveToItsBuild(Environment environment) {
		
		//Build buildFromEnvironment = environment.getBuilds().get(0);
		Build savedBuild = buildService.saveToItsBranch(environment.getBuilds().get(0));
		
		Environment environmentByNameAndMaster = environmentDao.findByNameAndMaster(environment.getName(), environment.getMaster());
		
		if (environmentByNameAndMaster != null) {
			if (!environmentByNameAndMaster.getBuilds().contains(savedBuild)) {
				environmentByNameAndMaster.getBuilds().add(savedBuild);
			}
			return environmentByNameAndMaster;
		} else {
			List<Build> builds = new ArrayList<Build>();
			builds.add(savedBuild);
			environment.setBuilds(builds);
			return environmentDao.save(environment);
		}
		
	}

}

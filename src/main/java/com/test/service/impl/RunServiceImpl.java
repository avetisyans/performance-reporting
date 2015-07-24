package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.test.dao.RunDao;
import com.test.domain.Environment;
import com.test.domain.Run;
import com.test.exceptions.RunAlreadyExistsException;
import com.test.exceptions.RunDoesNotExistsException;
import com.test.service.EnvironmentService;
import com.test.service.RunService;

@Service("runService")
@Transactional
public class RunServiceImpl implements RunService {

	@Autowired
	private RunDao runDao;
	
	@Autowired
	private EnvironmentService environmentService;
	
	@Override
	public Run save(Run run) {
		return runDao.save(run);
	}

	@Override
	public Run saveToItsParent(Run run) {
		
		Run runFromDB = runDao.findByBuildNumber(run.getBuildNumber());
		
		if (runFromDB.getEndTime() == null) {
			return runFromDB;
		}
		
		throw new RunDoesNotExistsException(run.getBuildNumber());
	}
			
	private Run saveParentToDB(Run parentRun) {
			
			Run parentFromDB = runDao.findByBuildNumber(parentRun.getBuildNumber());
			
			if (parentFromDB != null) {
				return parentFromDB;
			}
			
			return runDao.save(parentRun);
	}		

	@Override
	public Run addEndTimeToExistingRun(Run runData) {
		Run runFromDB = runDao.findByBuildNumber(runData.getBuildNumber());
		
		if (runFromDB.getEndTime() == null) {
			runFromDB.setEndTime(runData.getEndTime());
			return runFromDB;
		}
		
		throw new RunDoesNotExistsException(runData.getBuildNumber());
	}

	@Override
	public List<Run> findAll() {
		return runDao.findAll();
	}

	@Override
	public Run createRunWithParent(Run run) {
		
		Run runFromDB = runDao.findByBuildNumber(run.getBuildNumber());
		
		if (runFromDB != null) {
			throw new RunAlreadyExistsException(run.getBuildNumber());
		}
		
		Run parentRun = run.getParent();
		Run savedParent = saveParentToDB(parentRun);
		
		Environment envFromDB = environmentService.saveToItsBuild(run.getEnvironment());
		
		run.setParent(savedParent);
		run.setEnvironment(envFromDB);
		return runDao.save(run);
	}

	@Override
	public List<Run> findByEnvAndTestCase(Long envId, Long testCaseId, Pageable pageable) {
		return runDao.findByEnvAndTestCase(envId, testCaseId, pageable);
	}

}

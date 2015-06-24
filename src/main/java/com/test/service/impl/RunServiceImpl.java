package com.test.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.RunDao;
import com.test.domain.Run;
import com.test.service.RunService;

@Service("runService")
@Transactional
public class RunServiceImpl implements RunService {

	@Autowired
	private RunDao runDao;
	
	@Override
	public Run save(Run run) {
		return runDao.save(run);
	}

	@Override
	public Run saveToItsParent(Run run) {
		
		Run runFromDB = runDao.findByBuildNumber(run.getBuildNumber());
		
		if (runFromDB != null) {
			return runFromDB;
		}
		
		Run parentRun = run.getParent();
		Run savedParent = saveParentToDB(parentRun);
		run.setParent(savedParent);
		return runDao.save(run);
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
		
		if (runFromDB != null) {
			runFromDB.setEndTime(runData.getEndTime());
			return runFromDB;
		}
		
		return null;
	}

	@Override
	public List<Run> findAll() {
		return runDao.findAll();
	}

}

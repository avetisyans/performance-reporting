package com.test.service.impl;

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
	public Run saveToDB(Run run) {
		
		Run runFromDB = runDao.findByBuildNumber(run.getBuildNumber());
		
		if (runFromDB != null) {
			return runFromDB;
		}
		
		return runDao.save(run);
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

}

package com.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.BuildDao;
import com.test.domain.Branch;
import com.test.domain.Build;
import com.test.service.BranchService;
import com.test.service.BuildService;

@Service("buildService")
@Transactional
public class BuildServiceImpl implements BuildService {

	@Autowired
	private BuildDao buildDao;

	@Autowired
	private BranchService branchService;
	
	@Override
	public Build saveToItsBranch(Build build) {
		Branch branchFromBuild = build.getBranch();
		
		Branch savedBranch = branchService.saveToDB(branchFromBuild);
		
		Build buildByBuildNumber = buildDao.findByBuildNumber(build.getBuildNumber());
		
		if (buildByBuildNumber != null) {
			buildByBuildNumber.setBranch(savedBranch);
			return buildByBuildNumber;
		} else {
			build.setBranch(savedBranch);
			return buildDao.save(build);
		}
	}

	@Override
	public Build saveToDB(Build build) {
		
		Build buildFromDB = buildDao.findByBuildNumber(build.getBuildNumber());
		
		if (buildFromDB != null) {
			return buildFromDB;
		}
		
		return buildDao.save(build);
	}
	

}

package com.test.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.BranchDao;
import com.test.domain.Branch;
import com.test.service.BranchService;

@Service("branchService")
@Transactional
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchDao branchDao;

	@Override
	public Branch saveToDB(Branch branch) {
		
		
		Branch branchFromDB = branchDao.findByName(branch.getName());
		
		if (branchFromDB != null) {
			return branchFromDB;
		}
		return branchDao.save(branch);
	}
	


}

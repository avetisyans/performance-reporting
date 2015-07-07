package com.test.service;

import java.util.List;

import com.test.domain.Run;


public interface RunService {
	Run save(Run run);

	Run saveToItsParent(Run run) throws Exception;

	Run addEndTimeToExistingRun(Run runData) throws Exception;
	
	List<Run> findAll();
}

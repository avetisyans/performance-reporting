package com.test.service;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.test.domain.Run;


public interface RunService {
	Run save(Run run);

	Run saveToItsParent(Run run) throws Exception;

	Run addEndTimeToExistingRun(Run runData) throws Exception;
	
	List<Run> findAll();

	Run createRunWithParent(Run runData);
	
	List<Run> findByEnvAndTestCase(Long envId, Long testCaseId, Pageable pageable);
}

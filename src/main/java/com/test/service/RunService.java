package com.test.service;

import com.test.domain.Run;


public interface RunService {
	Run save(Run run);

	Run saveToItsParent(Run run);

	Run addEndTimeToExistingRun(Run runData);
}
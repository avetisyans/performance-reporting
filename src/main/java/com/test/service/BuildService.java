package com.test.service;

import com.test.domain.Build;


public interface BuildService {
	Build saveToItsBranch(Build build);

	Build saveToDB(Build buildFromEnvironment);
}

package com.test.service;

import java.util.List;

import com.test.domain.Environment;

public interface EnvironmentService {

	List<Environment> findAll();
	
	Environment save(Environment environment);

	Environment saveToDB(Environment environment);
}

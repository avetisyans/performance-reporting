package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.Env_TestCase_TestResult;

@Repository("env_TestCase_TestResultDao")
public interface Env_TestCase_TestResultDao extends JpaRepository<Env_TestCase_TestResult, Long> {
	
}

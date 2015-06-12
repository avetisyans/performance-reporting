package com.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.Env_TestCase_TestResult;
import com.test.domain.TestCase;

@Repository("env_TestCase_TestResultDao")
public interface Env_TestCase_TestResultDao extends JpaRepository<Env_TestCase_TestResult, Long> {

	List<Env_TestCase_TestResult> findByTestCase(TestCase testCase);
	
}

package com.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;

@Repository("env_TestCase_TestResultDao")
public interface Run_TestCase_TestResultDao extends JpaRepository<Run_TestCase_TestResult, Long> {

	List<Run_TestCase_TestResult> findByTestCase(TestCase testCase);
	
}

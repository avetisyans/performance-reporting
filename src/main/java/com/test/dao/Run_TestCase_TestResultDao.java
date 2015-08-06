package com.test.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.domain.Run;
import com.test.domain.Run_TestCase_TestResult;
import com.test.domain.TestCase;

@Repository("env_TestCase_TestResultDao")
public interface Run_TestCase_TestResultDao extends JpaRepository<Run_TestCase_TestResult, Long> {

	List<Run_TestCase_TestResult> findByTestCase(TestCase testCase);
	Run_TestCase_TestResult findByRunAndTestCase(Run run, TestCase testCase);
	
	@Query("select rtt from Run_TestCase_TestResult rtt join rtt.run run where run.environment.id = :envId order by run.endTime desc")
	List<Run_TestCase_TestResult> findByEnvironment(@Param("envId") Long envId, Pageable pageable);
	
	
/*	@Query("select r from Run r join r.run_TestCase_TestResults rt join rt.testCase tc where r.environment.id = :envId and tc.id = :testCaseId order by r.endTime desc")
	List<Run> findByEnvAndTestCase(@Param("envId") Long envId, @Param("testCaseId") Long testCaseId, Pageable pageable);*/
	
}

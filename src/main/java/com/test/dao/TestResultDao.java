package com.test.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.domain.TestResult;

@Repository("testResultDao")
public interface TestResultDao extends JpaRepository<TestResult, Long>{
	
/*	@Query("select testresult from TestResult testresult join testresult.run_TestCase_TestResults rtt join rtt.testCase tc where tc.id = :testCaseId")
	TestResult findByTestCase(@Param("testCaseId") Long testCaseId);*/
	
	@Query("select testresult from TestResult testresult join testresult.run_TestCase_TestResults rtt join rtt.testCase tc where tc.id = :testCaseId and rtt.run.id = :runId")
	TestResult findByTestCaseAndRun(@Param("testCaseId") Long testCaseId, @Param("runId") Long runId);
	
/*	@Query("Select t from TestResult t where t.name = 'aaa' ")
	List<TestResult> findTestResultByName();*/
	
/*	@Query("Select t from TestResult t inner join  t.env_TestCase_TestResult e inner join TestCase tc inner join TestSuite ts inner join Run r where r.buildNumber = :buildNumber")
	List<TestResult> findByRun(String buildNumber);*/
	@Query("select tr from Run_TestCase_TestResult rtt join rtt.testResult tr join rtt.run run where run.environment.id = :envId and rtt.testCase.id = :testCaseId order by run.endTime desc")
	List<TestResult> findByEnvAndTestCase(@Param("envId") Long envId, @Param("testCaseId") Long testCaseId, Pageable pageable);
}	

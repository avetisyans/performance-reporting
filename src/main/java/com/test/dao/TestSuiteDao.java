package com.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.domain.TestSuite;

@Repository("testSuiteDao")
public interface TestSuiteDao extends JpaRepository<TestSuite, Long> {
	
	TestSuite findByName(String name);
	
	@Query("select distinct testsuite from TestSuite testsuite join testsuite.testCases tcs join tcs.run_TestCase_TestResults trr where trr.run.id = :runId")
	List<TestSuite> findByRun(@Param("runId") Long runId);
	
	
}

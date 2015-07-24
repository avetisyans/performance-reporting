package com.test.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.test.domain.Run;

@Repository("runDao")
public interface RunDao extends JpaRepository<Run, Long> {

	Run findByBuildNumber(String buildNumber);

/*	@Query("Select r FROM Run r INNER JOIN r.testSuites t WHERE t.id = :testSuiteId ")
	List<Run> findByTestSuite(Long testSuiteId);*/
	
	@Query("select r.buildNumber from Run r join r.run_TestCase_TestResults rt join rt.testCase tc where r.environment.id = :envId and tc.id = :testCaseId order by r.endTime desc")
	List<Run> findByEnvAndTestCase(@Param("envId") Long envId, @Param("testCaseId") Long testCaseId, Pageable pageable);
	
/*	@Query("select testresult from TestResult testresult join testresult.run_TestCase_TestResults rtt join rtt.testCase tc where tc.id = :testCaseId and rtt.run.id = :runId")
	TestResult findByTestCaseAndRun(@Param("testCaseId") Long testCaseId, @Param("runId") Long runId);*/
	
}

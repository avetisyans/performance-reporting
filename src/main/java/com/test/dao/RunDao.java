package com.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.domain.Run;
import com.test.domain.TestSuite;

@Repository("runDao")
public interface RunDao extends JpaRepository<Run, Long> {

	Run findByBuildNumber(String buildNumber);

	@Query("Select r FROM Run r INNER JOIN r.testSuites t WHERE t.id = :testSuiteId ")
	List<Run> findByTestSuite(Long testSuiteId);
	
}

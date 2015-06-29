package com.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.test.domain.TestResult;

@Repository("testResultDao")
public interface TestResultDao extends JpaRepository<TestResult, Long>{

/*	@Query("Select t from TestResult t where t.name = 'aaa' ")
	List<TestResult> findTestResultByName();*/
	
/*	@Query("Select t from TestResult t inner join  t.env_TestCase_TestResult e inner join TestCase tc inner join TestSuite ts inner join Run r where r.buildNumber = :buildNumber")
	List<TestResult> findByRun(String buildNumber);*/
}

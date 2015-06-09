package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.TestResult;

@Repository("testResultDao")
public interface TestResultDao extends JpaRepository<TestResult, Long>{

/*	@Query("Select t from TestResult t where t.name = 'aaa' ")
	List<TestResult> findTestResultByName();*/
}

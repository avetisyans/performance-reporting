package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.TestSuite;

@Repository("testSuiteDao")
public interface TestSuiteDao extends JpaRepository<TestSuite, Long> {

	TestSuite findByName(String name);
	
}

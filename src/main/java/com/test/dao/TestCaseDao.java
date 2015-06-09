package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.TestCase;

@Repository("testCaseDao")
public interface TestCaseDao extends JpaRepository<TestCase, Long> {

	TestCase findByName(String name);
	
}

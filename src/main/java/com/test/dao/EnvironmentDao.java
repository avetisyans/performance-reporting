package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.Environment;

@Repository("environmentDao")
public interface EnvironmentDao extends JpaRepository<Environment, Long> {

	Environment findByBuildNumberAndMaster(String buildNumber, String master);
	
}

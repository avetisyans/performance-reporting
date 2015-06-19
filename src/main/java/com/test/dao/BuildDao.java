package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.Build;

@Repository("buildDao")
public interface BuildDao extends JpaRepository<Build, Long> {

	Build findByBuildNumber(String buildNumber);
	
}

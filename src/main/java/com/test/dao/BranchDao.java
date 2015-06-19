package com.test.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.test.domain.Branch;

@Repository("branchDao")
public interface BranchDao extends JpaRepository<Branch, Long> {

	Branch findByName(String name);
	
}

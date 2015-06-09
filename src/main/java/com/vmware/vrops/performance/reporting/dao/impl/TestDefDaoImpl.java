package com.vmware.vrops.performance.reporting.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.vmware.vrops.performance.reporting.dao.TestDefDao;
import com.vmware.vrops.performance.reporting.domain.TestDef;

@Repository("testDefDao")
public class TestDefDaoImpl implements TestDefDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TestDef testDef) {
		entityManager.persist(testDef);

	}

	@Override
	public List<TestDef> findAll() {
		List<TestDef> testDef = entityManager.createQuery("SELECT d FROM TestDef d").getResultList();
		return testDef;
	}

}

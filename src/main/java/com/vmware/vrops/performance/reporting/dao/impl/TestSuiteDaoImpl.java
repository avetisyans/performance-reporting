package com.vmware.vrops.performance.reporting.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vmware.vrops.performance.reporting.dao.TestSuiteDao;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;
import com.vmware.vrops.performance.reporting.domain.TestSuite;

@Repository("testSuiteDao")
public class TestSuiteDaoImpl implements TestSuiteDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TestSuite testSuite) {
		entityManager.persist(testSuite);
	}

	@Override
	public TestSuite update(TestSuite testSuite) {
		return entityManager.merge(testSuite);
	}

	@Override
	public void delete(TestSuite testSuite) {
		entityManager.remove(testSuite);
	}

	@Override
	public TestSuite findById(Long id) {
		return entityManager.find(TestSuite.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestSuite> findByName(String name) {
		Query query = entityManager
				.createQuery("SELECT t FROM TestSuite t WHERE t.name=:name");
		query.setParameter("name", name);
		List<TestSuite> testSuites = query.getResultList();
		return testSuites;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestSuite> findAll() {
		List<TestSuite> testSuites = entityManager.createQuery(
				"SELECT t FROM TestSuite t").getResultList();
		return testSuites;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<String> findSuiteNames() {
		List<String> suiteNames = entityManager.createQuery(
				"SELECT d.name FROM TestSuite d GROUP BY d.name ")
				.getResultList();
		return suiteNames;
	}

}

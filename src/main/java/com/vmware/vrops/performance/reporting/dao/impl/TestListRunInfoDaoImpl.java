package com.vmware.vrops.performance.reporting.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vmware.vrops.performance.reporting.dao.TestListRunInfoDao;
import com.vmware.vrops.performance.reporting.domain.TestListRunInfo;

@Repository("testListRunDao")
public class TestListRunInfoDaoImpl implements TestListRunInfoDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TestListRunInfo testListRunInfo) {
		entityManager.persist(testListRunInfo);
	}

	@Override
	public TestListRunInfo update(TestListRunInfo testListRunInfo) {
		return entityManager.merge(testListRunInfo);
	}

	@Override
	public void delete(TestListRunInfo testListRunInfo) {
		entityManager.remove(testListRunInfo);
	}

	@Override
	public TestListRunInfo findById(Long id) {
		return entityManager.find(TestListRunInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestListRunInfo> findByName(String name) {
		Query query = entityManager.createQuery("SELECT t FROM TestListRunInfo t left join fetch t.testDef WHERE t.testDef.name=:name");
		query.setParameter("name", name);
		List<TestListRunInfo> testListRuns = query.getResultList();
		return testListRuns;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestListRunInfo> findAll() {
		List<TestListRunInfo> testListRunInfos = entityManager.createQuery("SELECT t FROM TestListRunInfo t").getResultList();
		return testListRunInfos;
	}


	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<TestListRunInfo> findBySuiteId(String suiteId) {
		Query query = entityManager.createQuery("SELECT t FROM TestListRunInfo t left join fetch t.testSuite WHERE t.testSuite.id=:suiteId");
		Long id = new Long(suiteId);
		query.setParameter("suiteId", id);
		List<TestListRunInfo> testListRunInfos = query.getResultList();
		return testListRunInfos;

	}

}

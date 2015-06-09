package com.vmware.vrops.performance.reporting.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vmware.vrops.performance.reporting.dao.TestRunInfoDao;
import com.vmware.vrops.performance.reporting.domain.TestRunInfo;

@Repository("testRunInfoDao")
public class TestRunInfoDaoImpl implements TestRunInfoDao{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(TestRunInfo test) {
		entityManager.persist(test);

	}

	@Override
	public TestRunInfo update(TestRunInfo testRunInfo) {
		return entityManager.merge(testRunInfo);

	}

	@Override
	public void delete(TestRunInfo testRunInfo) {
		entityManager.remove(testRunInfo);

	}

	@Override
	public TestRunInfo findById(Long id) {
		return entityManager.find(TestRunInfo.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestRunInfo> findByTestDefName(String name) {
		Query query = entityManager.createQuery("SELECT t FROM TestRunInfo t right join fetch t.testDef WHERE  t.testDef.name=:name");
		query.setParameter("name", name);
		List<TestRunInfo> testRunInfos = query.getResultList();
		return testRunInfos;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TestRunInfo> findAll() {
		List<TestRunInfo> testRunInfos = entityManager.createQuery("SELECT t FROM TestRunInfo t").getResultList();
		return testRunInfos;
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEm(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public List<TestRunInfo> findByTestDefNameAndEnv(String testDefName,
			String environmentName) {
		Query query = entityManager.createQuery("SELECT t FROM TestRunInfo t right join fetch t.testDef right join fetch t.environmentConfig WHERE  t.testDef.name=:testDefName AND t.environmentConfig.name=:environmentName");
		query.setParameter("testDefName", testDefName);
		query.setParameter("environmentName", environmentName);
		List<TestRunInfo> testRunInfos = query.getResultList();
		return testRunInfos;
	}
}

package com.vmware.vrops.performance.reporting.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import javax.persistence.Query;

import com.vmware.vrops.performance.reporting.dao.EnvironmentConfigDao;
import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;

@Repository("environmentConfigDao")
public class EnvironmentConfigDaoImpl implements EnvironmentConfigDao {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(EnvironmentConfig environmentConfig) {
		entityManager.persist(environmentConfig);

	}

	// group by name will be removed after adding appropriate environment_definition table
	@Override
	public List<String> findEnvironmentNames() {
		List<String>  environmentNames = 	entityManager
				.createQuery(
						"SELECT d.name AS name FROM EnvironmentConfig d GROUP BY d.name ")
				.getResultList();
		return environmentNames;
	}

	@Override
	public List<EnvironmentConfig> findEnvironmentConfigs() {
		List<EnvironmentConfig>  environmentNames = 	entityManager
				.createQuery(
						"SELECT d FROM EnvironmentConfig d")
				.getResultList();
		return environmentNames;
	}

	@Override
	public List<EnvironmentConfig> findEnvironmentConfigsByName(String name) {
		Query query = entityManager.createQuery("SELECT d FROM EnvironmentConfig d WHERE d.name=:name");
		query.setParameter("name", name);
		List<EnvironmentConfig> environmentNames = query.getResultList();
		return environmentNames;
	}

}

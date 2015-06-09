package com.vmware.vrops.performance.reporting.dao.impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.vmware.vrops.performance.reporting.dao.DailyRunInfoDao;
import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;

@Repository("dailyRunInfoDao")
public class DailyRunInfoDaoImpl implements DailyRunInfoDao{

//
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(DailyRunInfo dailyRunInfo) {
		entityManager.persist(dailyRunInfo);
		entityManager.flush();
	}

	@Override
	public DailyRunInfo findById(Long id) {
		return entityManager.find(DailyRunInfo.class, id);
	}

	@Override
	public List<DailyRunInfo> findByDate(String date) {
		Query query = entityManager.createQuery("SELECT d FROM DailyRunInfo d WHERE to_char(d.date, 'YYYY-MM-DD')=:date");
		query.setParameter("date", date);
		List<DailyRunInfo> dailyRunInfos = query.getResultList();
		return dailyRunInfos;
	}

	@Override
	public List<DailyRunInfo> findAll() {
		List<DailyRunInfo> dailyRunInfos = entityManager.createQuery("SELECT d FROM DailyRunInfo d").getResultList();
		return dailyRunInfos;
	}
		// TODO add date range selection
	@Override
	public List findHistory(String startDate, String endDate, String suiteName,
			String testName) {
		Query query = entityManager
				.createQuery("SELECT  dtttt.name, dtt.name,dt.environmentConfig.name,d.date, dttt.id, dttt.max, dttt.min, dttt.avg, dttt.numberOfRuns, dttt.totalPass "
						+ "FROM DailyRunInfo AS d "
						+ "join  d.testSuitesPerEnv AS dt "
						+ "join dt.environmentConfig as dte "
						+ "join dt.testSuites as dtt "
						+ "join dtt.testListRunInfosSet as dttt "
						+ "join dttt.testDef as dtttt "
						+ " WHERE dtt.name=:suiteName and dtttt.name=:testName"
						+ " order by d.date");
		query.setParameter("suiteName", suiteName);
		query.setParameter("testName", testName);
		List history = query.getResultList();
		return history;
	}

		@Override
		public void merge(DailyRunInfo dailyRunInfo) {
			// TODO Auto-generated method stub

		}

}

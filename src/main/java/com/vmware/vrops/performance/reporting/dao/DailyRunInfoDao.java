package com.vmware.vrops.performance.reporting.dao;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;


public interface DailyRunInfoDao {

	void save(DailyRunInfo dailyRunInfo);
	
	void merge (DailyRunInfo dailyRunInfo);
	
	DailyRunInfo findById(Long id);

	List<DailyRunInfo> findByDate(String date);

	List<DailyRunInfo> findAll();

	List findHistory(String startDate, String endDate, String suiteName,
			String testName);
}

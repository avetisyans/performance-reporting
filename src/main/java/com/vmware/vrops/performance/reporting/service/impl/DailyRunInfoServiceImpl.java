package com.vmware.vrops.performance.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vrops.performance.reporting.dao.DailyRunInfoDao;
import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.service.DailyRunInfoService;

@Service("dailyRunInfoService")
@Transactional
public class DailyRunInfoServiceImpl implements DailyRunInfoService {

	@Autowired
	private DailyRunInfoDao dailyRunInfoDao;

	@Override
	public void save(DailyRunInfo dailyRunInfo) {
		dailyRunInfoDao.save(dailyRunInfo);

	}

	@Override
	public DailyRunInfo findById(Long id) {
		return dailyRunInfoDao.findById(id);
	}

	@Override
	public List<DailyRunInfo> findByDate(String date) {
		return dailyRunInfoDao.findByDate(date);
	}

	@Override
	public List<DailyRunInfo> findAll() {
		return dailyRunInfoDao.findAll();
	}

	@Override
	public List findHistory(String startDate, String endDate, String suiteName,
			String testName) {
		return dailyRunInfoDao.findHistory(startDate, endDate, suiteName,
				testName);
	}

	@Override
	public void merge(DailyRunInfo dailyRunInfo) {
		dailyRunInfoDao.merge(dailyRunInfo);		
	}

}

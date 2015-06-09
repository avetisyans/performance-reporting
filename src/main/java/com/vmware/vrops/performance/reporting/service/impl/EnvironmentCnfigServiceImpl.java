package com.vmware.vrops.performance.reporting.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vmware.vrops.performance.reporting.dao.EnvironmentConfigDao;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;
import com.vmware.vrops.performance.reporting.service.EnvironmentConfigService;

@Service("environmentConfigService")
@Transactional
public class EnvironmentCnfigServiceImpl implements EnvironmentConfigService {

	@Autowired
	private EnvironmentConfigDao environmentCnfigDao;


	@Override
	public void save(EnvironmentConfig environmentCnfig) {
		environmentCnfigDao.save(environmentCnfig);

	}

	@Override
	public List<String> findEnvironmentNames() {
		return environmentCnfigDao.findEnvironmentNames();

	}

	@Override
	public List<EnvironmentConfig> findEnvironmentConfigs() {
		return environmentCnfigDao.findEnvironmentConfigs();
	}

	@Override
	public List<EnvironmentConfig> findEnvironmentConfigsByName(String name) {
		return environmentCnfigDao.findEnvironmentConfigsByName(name);
	}

}

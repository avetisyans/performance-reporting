package com.vmware.vrops.performance.reporting.dao;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.DailyRunInfo;
import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;

public interface EnvironmentConfigDao {

	void save(EnvironmentConfig environmentConfig);

	List<String> findEnvironmentNames();

	List<EnvironmentConfig> findEnvironmentConfigs();

	List<EnvironmentConfig> findEnvironmentConfigsByName(String name);

}

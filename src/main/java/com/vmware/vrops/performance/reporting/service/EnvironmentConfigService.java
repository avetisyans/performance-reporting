package com.vmware.vrops.performance.reporting.service;

import java.util.List;

import com.vmware.vrops.performance.reporting.domain.EnvironmentConfig;

public interface EnvironmentConfigService {

	void save(EnvironmentConfig EnvironmentConfig);

	List<String> findEnvironmentNames();

	List<EnvironmentConfig> findEnvironmentConfigs();

	List<EnvironmentConfig> findEnvironmentConfigsByName(String name);
}

package com.vmware.vrops.performance.reporting.domain;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

//@Entity
@Table(name="test_suites_per_env")
public class TestSuiteListPerEnv {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne(cascade = CascadeType.ALL)
	//@JsonBackReference
	private EnvironmentConfig environmentConfig;

	@ManyToOne
	private DailyRunInfo dailyRunInfo;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "test_suite_list", joinColumns = { @JoinColumn(name = "test_suite_iD") })
	private Set<TestSuite> testSuites = new LinkedHashSet<TestSuite>();

	public Set<TestSuite> getTestSuites() {
		return testSuites;
	}

	public void setTestSuites(Set<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

	public Long getId() {
		return id;
	}

	public EnvironmentConfig getEnvironmentConfig() {
		return environmentConfig;
	}

	public void setEnvironmentConfig(EnvironmentConfig environmentConfig) {
		this.environmentConfig = environmentConfig;
	}

	public DailyRunInfo getDailyRunInfo() {
		return dailyRunInfo;
	}

	public void setDailyRunInfo(DailyRunInfo dailyRunInfo) {
		this.dailyRunInfo = dailyRunInfo;
	}

}

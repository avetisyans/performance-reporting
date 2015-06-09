package com.vmware.vrops.performance.reporting.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


//@Entity
@Table(name="daily_run_info")
public class DailyRunInfo implements Serializable{


	/**
	 *
	 */
	private static final long serialVersionUID = 2131085065391065653L;

	@Id
	@GeneratedValue
	private Long id;

	private Date date;


	@OneToMany(fetch=FetchType.EAGER,cascade=CascadeType.ALL, mappedBy="dailyRunInfo")
//	@JoinTable(name = "daily_run_per_env_suite", joinColumns = { @JoinColumn(name = "daily_run_iD") })
	private Set<TestSuiteListPerEnv> testSuitesPerEnv = new LinkedHashSet<TestSuiteListPerEnv>();

	public Long getId() {
		return id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Set<TestSuiteListPerEnv> getTestSuitesPerEnv() {
		return testSuitesPerEnv;
	}

	public void setTestSuitesPerEnv(Set<TestSuiteListPerEnv> testSuitesPerEnv) {
		this.testSuitesPerEnv = testSuitesPerEnv;
	}




}

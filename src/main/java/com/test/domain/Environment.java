package com.test.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Environment {
	@Id
	@GeneratedValue
	private Long id;

	private String buildNumber;

	private String name;

	private String branch;

	private String productName;
	
	private String[] datanodes;

	private String master;

	private boolean haEnabled;
	
	@OneToMany(mappedBy="environment", cascade = CascadeType.ALL)
	private List<Env_TestCase_TestResult> env_TestCase_TestResults;

	public String getBranch() {
		return branch;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public String[] getDatanodes() {
		return datanodes;
	}

	public List<Env_TestCase_TestResult> getEnv_TestCase_TestResults() {
		return env_TestCase_TestResults;
	}
	
	public Long getId() {
		return id;
	}

	public String getMaster() {
		return master;
	}

	public String getName() {
		return name;
	}

	public String getProductName() {
		return productName;
	}

	public boolean isHaEnabled() {
		return haEnabled;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}


	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public void setDatanodes(String[] datanodes) {
		this.datanodes = datanodes;
	}

	public void setEnv_TestCase_TestResults(
			List<Env_TestCase_TestResult> env_TestCase_TestResults) {
		this.env_TestCase_TestResults = env_TestCase_TestResults;
	}

	public void setHaEnabled(boolean haEnabled) {
		this.haEnabled = haEnabled;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}


}

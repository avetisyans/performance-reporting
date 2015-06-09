package com.vmware.vrops.performance.reporting.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;


//@Entity
@Table(name = "environment_config")
public class EnvironmentConfig implements Serializable {

	/**
    *
    */
	private static final long serialVersionUID = -860632745046724800L;

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

	public static enum DeploymentType {
		VA, WIN_STANDALONE, LINUX_STANDALONE
	};

	@Enumerated(EnumType.STRING)
	private DeploymentType deploymenttype;
////new added
	@OneToOne(cascade = CascadeType.ALL)
	private TestSuiteListPerEnv testSuiteListPerEnv;

	public TestSuiteListPerEnv getTestSuiteListPerEnv() {
		return testSuiteListPerEnv;
	}

	public void setTestSuiteListPerEnv(TestSuiteListPerEnv testSuiteListPerEnv) {
		this.testSuiteListPerEnv = testSuiteListPerEnv;
	}

	public EnvironmentConfig() {
	}

	public EnvironmentConfig(String buildNumber, String branch,
			String productName, DeploymentType deploymentType) {
		this.buildNumber = buildNumber;
		this.branch = branch;
		this.productName = productName;
		this.deploymenttype = deploymentType;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Long getId() {
		return id;
	}

	public String[] getDatanodes() {
		return datanodes;
	}

	public void setDatanodes(String[] datanodes) {
		this.datanodes = datanodes;
	}

	public String getMaster() {
		return master;
	}

	public void setMaster(String master) {
		this.master = master;
	}

	public boolean isHaEnabled() {
		return haEnabled;
	}

	public void setHaEnabled(boolean haEnabled) {
		this.haEnabled = haEnabled;
	}

	public DeploymentType getDeploymenttype() {
		return deploymenttype;
	}

	public void setDeploymenttype(DeploymentType deploymenttype) {
		this.deploymenttype = deploymenttype;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

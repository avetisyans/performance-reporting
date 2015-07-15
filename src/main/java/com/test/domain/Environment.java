package com.test.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Environment {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
	private String productName;
	
	private String[] datanodes;
	
	private String master;
	
	private String deploymentType;
	
	private boolean haEnabled;
	
	@OneToMany(mappedBy="environment")
	private List<Run> runs;
	
	@ManyToMany
	private List<Build> builds;
	
/*	@OneToMany(mappedBy="environment")
	private List<Env_TestCase_TestResult> env_TestCase_TestResults;*/
	
	public List<Build> getBuilds() {
		return builds;
	}
	
	
	public String[] getDatanodes() {
		return datanodes;
	}
	
	
	public String getDeploymentType() {
		return deploymentType;
	}
	
/*	public List<Env_TestCase_TestResult> getEnv_TestCase_TestResults() {
		return env_TestCase_TestResults;
	}*/
	
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

	public void setBuilds(List<Build> builds) {
		this.builds = builds;
	}

	public void setDatanodes(String[] datanodes) {
		this.datanodes = datanodes;
	}

	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}

/*	public void setEnv_TestCase_TestResults(
			List<Env_TestCase_TestResult> env_TestCase_TestResults) {
		this.env_TestCase_TestResults = env_TestCase_TestResults;
	}*/

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


	public List<Run> getRuns() {
		return runs;
	}


	public void setRuns(List<Run> runs) {
		this.runs = runs;
	}



}

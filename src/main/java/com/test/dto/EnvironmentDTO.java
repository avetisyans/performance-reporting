package com.test.dto;

import java.util.ArrayList;
import java.util.List;

import com.test.domain.Build;
import com.test.domain.Environment;

public class EnvironmentDTO {

	private List<BuildWithBranchDTO> buildAndBranches;
	private String name;
	private String productName;
	private String[] datanodes;
	private String master;
	private String deploymentType;
	private boolean haEnabled;

	public EnvironmentDTO(Environment environment) {
		this.name = environment.getName();
		this.productName = environment.getProductName();
		this.datanodes = environment.getDatanodes();
		this.master = environment.getMaster();
		this.deploymentType = environment.getDeploymentType();
		this.haEnabled = environment.isHaEnabled();
		List<Build> builds = environment.getBuilds();
		List<BuildWithBranchDTO> buildWbranches = new ArrayList<BuildWithBranchDTO>();
		
		for (Build build : builds) {
			BuildWithBranchDTO bldWbrach = new BuildWithBranchDTO();
			bldWbrach.setBuildNumber(build.getBuildNumber());
			bldWbrach.setBranchName(build.getBranch().getName());
			buildWbranches.add(bldWbrach);
		}
		this.buildAndBranches = buildWbranches;
		//this.buildWithBranchDTOs = environment.get
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public String getDeploymentType() {
		return deploymentType;
	}

	public void setDeploymentType(String deploymentType) {
		this.deploymentType = deploymentType;
	}

	public boolean isHaEnabled() {
		return haEnabled;
	}

	public void setHaEnabled(boolean haEnabled) {
		this.haEnabled = haEnabled;
	}


	public List<BuildWithBranchDTO> getBuildAndBranches() {
		return buildAndBranches;
	}


	public void setBuildAndBranches(List<BuildWithBranchDTO> buildAndBranches) {
		this.buildAndBranches = buildAndBranches;
	}

}

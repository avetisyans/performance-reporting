package com.test.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Build {
	@Id
	@GeneratedValue
	private Long id;
	
	private String buildNumber;
	
	@ManyToOne
	@JoinColumn(name="branch_id")
	private Branch branch;
	
	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	@ManyToMany(mappedBy="builds")
	private List<Environment> environments;

	public String getBuildNumber() {
		return buildNumber;
	}

	public List<Environment> getEnvironments() {
		return environments;
	}

	public Long getId() {
		return id;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public void setEnvironments(List<Environment> environments) {
		this.environments = environments;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Build [id=" + id + ", buildNumber=" + buildNumber + ", branch="
				+ branch.getName();
	}

}

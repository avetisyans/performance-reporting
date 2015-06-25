package com.test.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.util.DurationConverter;

@Entity
public class Run {
	@Id
	@GeneratedValue
	private Long id;
	
	private String buildNumber;
	
	private String tag;
	
	@OneToMany(mappedBy="parent", fetch=FetchType.EAGER)
	private List<Run> children;
	
	@ManyToOne
	private Run parent;
	
	@ManyToMany(mappedBy="runs")
	private List<TestSuite> testSuites;
	
	private Long startTime;
	
	private Long endTime;
	
	@Transient
	@JsonIgnore
	private String duration;
	
	@PostLoad
	@PostUpdate
	public void calculateDuration() {
		if (this.endTime != null && this.startTime != null) {
			long dur = this.endTime - this.startTime;
			this.duration = DurationConverter.formatToString(dur);
		}
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public List<Run> getChildren() {
		return children;
	}


	public Long getEndTime() {
		return endTime;
	}

	public Long getId() {
		return id;
	}

	public Run getParent() {
		return parent;
	}

	public Long getStartTime() {
		return startTime;
	}

	public String getTag() {
		return tag;
	}

	public List<TestSuite> getTestSuites() {
		return testSuites;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public void setChildren(List<Run> children) {
		this.children = children;
	}


	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setParent(Run parent) {
		this.parent = parent;
	}

	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTestSuites(List<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
	

	

	
}

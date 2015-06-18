package com.test.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	List<TestSuite> testSuites;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;

	public String getBuildNumber() {
		return buildNumber;
	}

	public List<Run> getChildren() {
		return children;
	}

	public Date getEndTime() {
		return endTime;
	}

	public Long getId() {
		return id;
	}

	public Run getParent() {
		return parent;
	}

	public Date getStartTime() {
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

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setParent(Run parent) {
		this.parent = parent;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTestSuites(List<TestSuite> testSuites) {
		this.testSuites = testSuites;
	}

	@Override
	public String toString() {
		return "Run [id=" + id + ", buildNumber=" + buildNumber + ", tag="
				+ tag + ", children=" + children + ", parent=" + parent
				+ ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}


	
}

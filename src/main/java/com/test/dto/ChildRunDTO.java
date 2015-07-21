package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class ChildRunDTO {
	private Long id;
	private String buildNumber;
	private String tag;
	private Long startTime;
	private Long endTime;
	private String duration;
	private EnvironmentDTO environment;
	//private List<TestCaseWithResultDTO> TestCases;
	private List<TestSuiteWithResultDTO> testSuites = new ArrayList<TestSuiteWithResultDTO>();
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getBuildNumber() {
		return buildNumber;
	}
	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public Long getStartTime() {
		return startTime;
	}
	public void setStartTime(Long startTime) {
		this.startTime = startTime;
	}
	public Long getEndTime() {
		return endTime;
	}
	public void setEndTime(Long endTime) {
		this.endTime = endTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
/*	public List<TestCaseWithResultDTO> getTestCases() {
		return TestCases;
	}
	public void setTestCases(List<TestCaseWithResultDTO> testCases) {
		TestCases = testCases;
	}*/
	public EnvironmentDTO getEnvironment() {
		return environment;
	}
	public void setEnvironment(EnvironmentDTO environment) {
		this.environment = environment;
	}
	public List<TestSuiteWithResultDTO> getTestSuites() {
		return testSuites;
	}
	public void setTestSuites(List<TestSuiteWithResultDTO> testSuites) {
		this.testSuites = testSuites;
	}
}

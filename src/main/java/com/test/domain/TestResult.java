package com.test.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class TestResult {
	public static enum TestType {
		API, UI
	}
	
	public static enum Result {
		NONE, SUCCESS, FAILURE, SKIPPED
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Result result;
	
	@Enumerated(EnumType.STRING)	
	private TestType testType;

	@Temporal(TemporalType.TIMESTAMP)
	private Date startTime;

	@Temporal(TemporalType.TIMESTAMP)
	private Date endTime;
	
	@OneToMany(mappedBy="testResult")
	private List<Env_TestCase_TestResult> env_TestCase_TestResults;
	
	public Date getEndTime() {
		return endTime;
	};

	public List<Env_TestCase_TestResult> getEnv_TestCase_TestResults() {
		return env_TestCase_TestResults;
	}

	public Long getId() {
		return id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public void setEnv_TestCase_TestResults(List<Env_TestCase_TestResult> env_TestCase_TestResults) {
		this.env_TestCase_TestResults = env_TestCase_TestResults;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
	}
}

package com.test.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Env_TestCase_TestResult {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="environment_id")
	private Environment environment;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="testCase_id")
	private TestCase testCase;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="testResult_id")
	private TestResult testResult;
	
	
	public Environment getEnvironment() {
		return environment;
	}

	public Long getId() {
		return id;
	}

	public TestCase getTestCase() {
		return testCase;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
	
	
	
	
}

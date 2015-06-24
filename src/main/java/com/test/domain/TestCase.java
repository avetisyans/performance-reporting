package com.test.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class TestCase {
	
	public static enum TestType {
		API, UI
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@Enumerated(EnumType.STRING)	
	private TestType testType;
	
	@ManyToOne
	@JoinColumn(name="testSuite_id")
	private TestSuite testSuite;
	
	@OneToMany(mappedBy="testCase")
	private List<Env_TestCase_TestResult> env_TestCase_TestResults;
	
	
	public List<Env_TestCase_TestResult> getEnv_TestCase_TestResults() {
		return env_TestCase_TestResults;
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}

	public TestSuite getTestSuite() {
		return testSuite;
	}

	public TestType getTestType() {
		return testType;
	}

	public void setEnv_TestCase_TestResults(List<Env_TestCase_TestResult> env_TestCase_TestResults) {
		this.env_TestCase_TestResults = env_TestCase_TestResults;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public void setName(String name) {
		this.name = name;
	}

	public void setTestSuite(TestSuite testSuite) {
		this.testSuite = testSuite;
	}

	public void setTestType(TestType testType) {
		this.testType = testType;
	}

}

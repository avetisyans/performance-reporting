package com.test.domain;

import java.util.List;

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
	
	//@Column(unique=true)
	private String name;
	
	@Enumerated(EnumType.STRING)	
	private TestType testType;
	
	@ManyToOne
	@JoinColumn(name="testSuite_id")
	private TestSuite testSuite;
	
	@OneToMany(mappedBy="testCase")
	private List<Run_TestCase_TestResult> run_TestCase_TestResults;
	
	

	
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

	public List<Run_TestCase_TestResult> getRun_TestCase_TestResults() {
		return run_TestCase_TestResults;
	}

	public void setRun_TestCase_TestResults(
			List<Run_TestCase_TestResult> run_TestCase_TestResults) {
		this.run_TestCase_TestResults = run_TestCase_TestResults;
	}

}

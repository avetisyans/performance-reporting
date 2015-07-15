package com.test.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Run_TestCase_TestResult {
	
	@Id
	@GeneratedValue
	private Long id;
	
/*	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="environment_id")
	private Environment environment;
*/	
	@ManyToOne/*(cascade=CascadeType.ALL)*/
	@JoinColumn(name="run_id")
	private Run run;
	
	@ManyToOne/*(cascade=CascadeType.ALL)*/
	@JoinColumn(name="testCase_id")
	private TestCase testCase;
	
	@OneToOne/*(cascade=CascadeType.ALL)*/
	private TestResult testResult;
	
/*	public Environment getEnvironment() {
		return environment;
	}*/
	
	public Long getId() {
		return id;
	}
	
	public TestCase getTestCase() {
		return testCase;
	}
	
	public TestResult getTestResult() {
		return testResult;
	}
	
/*	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}*/
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public void setTestCase(TestCase testCase) {
		this.testCase = testCase;
	}
	
	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}
	
	@Override
	public String toString() {
		return "Env_TestCase_TestResult [run=" + run.getBuildNumber()
				+ ", testCase=" + testCase.getName() + ", testResult=" + testResult.getResult() + "]";
	}

	public Run getRun() {
		return run;
	}

	public void setRun(Run run) {
		this.run = run;
	}
	
}

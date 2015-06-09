package com.vmware.vrops.performance.reporting.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

//@Entity
@Table(name = "test_run_info")
public class TestRunInfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -2640499095392437341L;

	@Id
	@GeneratedValue
	private Long id;

/*	@ManyToOne
	private TestDef testDef;*/

	@ManyToOne
	@JoinColumn(name = "environmentConfig_id")
	private EnvironmentConfig environmentConfig;

	private Timestamp startTime;

	private Timestamp endTime;

	private Double executionTime;

	@ManyToOne
	@JoinColumn(name = "testListRunInfo_id")
	//@JsonBackReference
	private TestListRunInfo testListRunInfo;

	public static enum TestResult {
		PASS, FAILED, SKIPPED
	};

	@Enumerated(EnumType.STRING)
	private TestResult testResult;

	private byte[] image;

	private String logs;


	public EnvironmentConfig getEnvironmentConfig() {
		return environmentConfig;
	}

	public void setEnvironmentConfig(EnvironmentConfig environmentConfig) {
		this.environmentConfig = environmentConfig;
	}

	public TestRunInfo() {
	}

	public Long getId() {
		return id;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

/*	public TestDef getTestDef() {
		return testDef;
	}

	public void setTestDef(TestDef testDef) {
		this.testDef = testDef;
	}*/

	public TestListRunInfo getTestListRunInfo() {
		return testListRunInfo;
	}

	public void setTestListRunInfo(TestListRunInfo testListRunInfo) {
		this.testListRunInfo = testListRunInfo;
	}

	public TestResult getTestResult() {
		return testResult;
	}

	public void setTestResult(TestResult testResult) {
		this.testResult = testResult;
	}

	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}

	public String getLogs() {
		return logs;
	}

	public void setLogs(String logs) {
		this.logs = logs;
	}

	public Double getExecutionTime() {
		return executionTime;
	}

	public void setExecutionTime(Double executionTime) {
		this.executionTime = executionTime;
	}

}

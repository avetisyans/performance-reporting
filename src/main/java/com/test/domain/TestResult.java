package com.test.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PostLoad;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.test.util.DurationConverter;

@Entity
public class TestResult {
	
	public static enum Result {
		NONE, SUCCESS, FAILURE, SKIPPED
	}
	
	@Id
	@GeneratedValue
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private Result result;

	private Long startTime;

	private Long endTime;
	
	@OneToMany(mappedBy="testResult")
	private List<Env_TestCase_TestResult> env_TestCase_TestResults;
	
	@Transient
	@JsonIgnore
	private String duration;
	
	@PostLoad
	@PostUpdate
	public void calculateDuration() {
		long dur = this.endTime - this.startTime;
		this.duration = DurationConverter.formatToString(dur);
	}

	public List<Env_TestCase_TestResult> getEnv_TestCase_TestResults() {
		return env_TestCase_TestResults;
	}

	public Long getId() {
		return id;
	}

	public void setEnv_TestCase_TestResults(List<Env_TestCase_TestResult> env_TestCase_TestResults) {
		this.env_TestCase_TestResults = env_TestCase_TestResults;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Result getResult() {
		return result;
	}

	public void setResult(Result result) {
		this.result = result;
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
}

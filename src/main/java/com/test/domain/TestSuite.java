package com.test.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class TestSuite {
	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique=true)
	private String name;
	
/*	@ManyToMany(mappedBy="testClasses")
	private List<TestSet> testSets;*/
	
	@OneToMany(mappedBy="testSuite")
	private List<TestCase> testCases;
	
	@ManyToMany
	private List<Run> runs;

	public List<Run> getRuns() {
		return runs;
	}

	public void setRuns(List<Run> runs) {
		this.runs = runs;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<TestCase> getTestCases() {
		return testCases;
	}

/*	public List<TestSet> getTestSets() {
		return testSets;
	}*/

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTestCases(List<TestCase> testCases) {
		this.testCases = testCases;
	}

/*	public void setTestSets(List<TestSet> testSets) {
		this.testSets = testSets;
	}*/

}

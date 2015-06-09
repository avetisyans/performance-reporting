package com.test.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class TestSuite {
	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	
/*	@ManyToMany(mappedBy="testClasses")
	private List<TestSet> testSets;*/
	
	@OneToMany(mappedBy="testSuite", cascade = CascadeType.ALL)
	private List<TestCase> testCases;

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

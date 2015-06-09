package com.vmware.vrops.performance.reporting.domain;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//@Entity
@Table(name = "test_suite")
public class TestSuite implements Serializable {

	/**
    *
    */
	private static final long serialVersionUID = -8423964577048412497L;

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String tag;

	// private String status; // at this point we haven't defined yet pass/fail
	// criteria for test suites

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "testSuite")
	//@JsonManagedReference
	private Set<TestListRunInfo> testListRunInfosSet = new LinkedHashSet<TestListRunInfo>();

	public TestSuite(String name) {
		this.name = name;
	}

	public TestSuite() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TestListRunInfo> getTestListRunInfosSet() {
		return testListRunInfosSet;
	}

	public void setTestListRunInfosSet(Set<TestListRunInfo> tests) {
		this.testListRunInfosSet = tests;
	}

	public Long getId() {
		return id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}

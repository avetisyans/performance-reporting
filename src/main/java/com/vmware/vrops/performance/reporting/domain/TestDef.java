package com.vmware.vrops.performance.reporting.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

//@Entity
@Table(name = "test_def")
public class TestDef implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -7864997232661577670L;

	public static enum TestType {
		API, UI
	};

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	@Enumerated(EnumType.STRING)
	private TestType type;

	public TestDef() {

	}

	public TestDef(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public TestType getType() {
		return type;
	}

	public void setType(TestType type) {
		this.type = type;
	}

}

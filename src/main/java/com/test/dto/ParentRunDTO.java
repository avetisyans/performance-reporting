package com.test.dto;

import java.util.List;

public class ParentRunDTO {
	private Long id;
	private String buildNumber;
	private List<ChildRunDTO> children;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBuildNumber() {
		return buildNumber;
	}

	public void setBuildNumber(String buildNumber) {
		this.buildNumber = buildNumber;
	}

	public List<ChildRunDTO> getChildren() {
		return children;
	}

	public void setChildren(List<ChildRunDTO> children) {
		this.children = children;
	}
}

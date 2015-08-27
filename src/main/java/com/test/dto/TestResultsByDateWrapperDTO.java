package com.test.dto;

import java.util.ArrayList;
import java.util.List;

public class TestResultsByDateWrapperDTO {
	private List<TestResultsByDateDTO> chartData = new ArrayList<TestResultsByDateDTO>();

	public List<TestResultsByDateDTO> getChartData() {
		return chartData;
	}

	public void setChartData(List<TestResultsByDateDTO> chartData) {
		this.chartData = chartData;
	}
}

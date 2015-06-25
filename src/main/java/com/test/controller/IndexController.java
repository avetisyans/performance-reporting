package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.service.RunService;
import com.test.service.TestCaseService;
import com.test.service.TestResultService;
import com.test.service.TestSuiteService;

@Controller
public class IndexController {

	@Autowired
	private TestResultService testResultService;
	
	@Autowired
	private TestCaseService testCaseService;
	
	@Autowired
	private RunService runService;
	
	@Autowired
	private TestSuiteService testSuiteService;
	
	
	
	@RequestMapping("/")
	public String index(Model model) {
/*		model.addAttribute("results", testResultService.findAll());
		model.addAttribute("runs", runService.findAll());
		model.addAttribute("testCase", testCaseService.findOneWithResults(1L));*/
		model.addAttribute("testSuites", testSuiteService.findAll());
		return "index";
	}
}

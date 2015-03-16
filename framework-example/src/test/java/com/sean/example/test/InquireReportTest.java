package com.sean.example.test;

import com.sean.example.action.InquireReportAction;
import com.sean.unittest.annotation.ParameterConfig;
import com.sean.unittest.annotation.TestCaseConfig;
import com.sean.unittest.annotation.TestSuiteConfig;

@TestSuiteConfig(InquireReportAction.class)
public interface InquireReportTest
{
	@TestCaseConfig
	@ParameterConfig(name = "reportId", value = "3")
	public void testcase1();
}

package com.tests.utils;

import java.io.IOException;



import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;



public class MyTestNgListners implements ITestListener
{
	

	protected static ExtentReportsUtility extentreport=null;
	
	public void onTestStart(ITestResult result) {
		
		extentreport.startsingleTestReport(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		String method_name = result.getMethod().getMethodName();

		extentreport.logTestPassed(method_name);
		
	}

	public void onTestFailure(ITestResult result) {

		// TODO Auto-generated method stub
		String method_name = result.getMethod().getMethodName();
		extentreport.logTestFailed(method_name);
		

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport=ExtentReportsUtility.getInstance();
		extentreport.startExtentReport();
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extentreport.endReport();
	}
}

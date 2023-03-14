package com.tests.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.test.constants.SourcePath;

public class ExtentReportsUtility {
	public static ExtentReports report;
	public static ExtentSparkReporter spartReporter;
	public static ExtentTest testLogger;
	private static ExtentReportsUtility extentObject=null;
	private ExtentReportsUtility()
	{
		
	}
	public static ExtentReportsUtility getInstance() {
		if (extentObject==null)
		{
			extentObject=new ExtentReportsUtility();
			
		}
		return extentObject;
	}

	public void startExtentReport()
	{
		spartReporter=new ExtentSparkReporter(SourcePath.SPARKS_HTML_REPORT_PATH);
		report=new ExtentReports();
		report.attachReporter(spartReporter);
		
		report.setSystemInfo("Host Name", "Salesforce");
		report.setSystemInfo("Environment", "Automation Testing");
		report.setSystemInfo("User Name", "RaniChiliveri");

		spartReporter.config().setDocumentTitle("Test Execution Report");
		spartReporter.config().setReportName("Salesforce regression tests");
		spartReporter.config().setTheme(Theme.DARK);
		
	}
	public void startsingleTestReport(String testScript_Name)
	{
		testLogger=report.createTest(testScript_Name);
	}
	public void logTestInfo(String text)
	{
		testLogger.info(text);
	}
	public void logTestPassed(String testcaseName)
	{
		testLogger.pass(MarkupHelper.createLabel(testcaseName + "is passTest", ExtentColor.GREEN));
	}
	public void logTestFailed(String testcaseName)
	{
		testLogger.fail(MarkupHelper.createLabel(testcaseName + "is failed", ExtentColor.RED));
	}
	public void logTestFailedWithException(Exception e) {
		testLogger.log(Status.FAIL,e);
		}
	public void logTestScreenshot(String path) {
		testLogger.addScreenCaptureFromPath(path);
		}
	public void endReport() {
		report.flush();
	}
	
}

package com.amazon.TestingFiles;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.Constants;
//import utilities.excelUtility;
import utilities.reportPath;
import utilities.utility;

public class TC3_Books2_Chrome {
	
	WebDriver wd;
	utility search;
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void beforeClass() {
		report = reportPath.getInstance();
		test = report.startTest("TC 3: Test Book section");
		
		System.setProperty("webdriver.chrome.driver", "/Users/tatianakesler/Desktop/Selenium/installation/chromedriver");
		
		wd = new ChromeDriver();
		search = new utility(wd, test);
		
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		
		wd.get(Constants.Url);
		
		test.log(LogStatus.INFO, "WebBrowser started");
		test.log(LogStatus.INFO, "Window maximized");
		
//		excelUtility.setExcelFile(Constants.excelPath + Constants.excelFileName, "sheetNameHere");

	}

//	@BeforeMethod
//	@Parameters({"browser"})	
//	public void beforeMethod(String browser) {
//		if (browser.equalsIgnoreCase("firefox")){
//			System.setProperty("webdriver.gecko.driver", "/Users/tatianakesler/Desktop/Selenium/installation/geckodriver");
//			wd = new FirefoxDriver();
//		}
//		else if (browser.equalsIgnoreCase("chrome")){
//			System.setProperty("webdriver.chrome.driver", "/Users/tatianakesler/Desktop/Selenium/installation/chromedriver");
//			wd = new ChromeDriver();
//		}
//		
//		search = new utility(wd, test);
//		
//		wd.manage().window().maximize();
//		wd.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//		
//		wd.get(Constants.Url);
//		
//		test.log(LogStatus.INFO, "WebBrowser started");
//		test.log(LogStatus.INFO, "Window maximized");
//	}

	@Test
	public void BooksSectionTest() throws InterruptedException {
		search.hoverDepartments();
		search.chromeBooksAudible();
		search.chromeChildrenBooks();
		search.ClickFrom9to12();
		search.loopThroughAllBooks();
//		
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException {
		if (testResult.getStatus()==ITestResult.FAILURE){
			String path = utilities.FailedTCScreenshot.ScreenShotMethod.
					TakeScreenshot(wd, testResult.getName());
			String imgPath = test.addScreenCapture(path);
			test.log(LogStatus.FAIL, "Verification of TC failed", imgPath);
		}
		
	}

	@AfterClass
	public void afterClass() throws InterruptedException {
		Thread.sleep(3000);
		wd.quit();
		report.endTest(test);
		report.flush();
	}

}

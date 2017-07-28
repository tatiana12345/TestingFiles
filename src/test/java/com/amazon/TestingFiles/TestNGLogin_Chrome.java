package com.amazon.TestingFiles;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

//import org.apache.logging.log4j.LogManager;
//import org.apache.logging.log4j.Logger;
//import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
//import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.Constants;
import utilities.excelUtility;
import utilities.reportPath;
import utilities.utility;

public class TestNGLogin_Chrome {

	private WebDriver wd;
	utility search;
	ExtentReports report;
	ExtentTest test;

	@BeforeClass
	public void beforeClass() throws Exception {
		
		report = reportPath.getInstance();
		test = report.startTest("Verify login with valid credentials");
		
		System.setProperty("webdriver.chrome.driver", "/Users/tatianakesler/Desktop/Selenium/installation/chromedriver");
		
		wd = new ChromeDriver();
		
		wd.manage().window().maximize();
		wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		wd.get(Constants.Url);
		test.log(LogStatus.INFO, "WebBrowser started");
		test.log(LogStatus.INFO, "Browser maximized");
		
		search = new utility(wd, test);
		
		excelUtility.setExcelFile(Constants.excelPath + Constants.excelFileName, "LoginTests");
	}
//	@BeforeMethod
//	@Parameters({"browser"})
//	public void multyBrowserSetUp(String browser){
//		if (browser.equalsIgnoreCase("firefox")){
//			System.setProperty("webdriver.gecko.driver", "/Users/tatianakesler/Desktop/Selenium/installation/geckodriver");
//			
//			wd = new FirefoxDriver();
//		}
//			
//		else if(browser.equalsIgnoreCase("chrome")){
//			System.setProperty("webdriver.chrome.driver", "/Users/tatianakesler/Desktop/Selenium/installation/chromedriver");
//			
//			wd = new ChromeDriver();
//		}
//			wd.manage().window().maximize();
//			wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//
//			wd.get(Constants.Url);
//
//			test.log(LogStatus.INFO, "WebBrowser started");
//			test.log(LogStatus.INFO, "Browser maximized");
//		}
	
	@DataProvider (name="logindata")
	public Object[][] DataProvider(){
		Object[][] testData = excelUtility.getTestData("test");
		return testData;
	}

	@Test(dataProvider="logindata")
	public void loginTestCase(String username, String password) {
		search.clickLogin();
		search.typeEmail(username);
		search.typePassword(password);
		search.clickSubmit();
	//	search.verifyLoggedIn();
	boolean result = search.isPresent();
		Assert.assertTrue(result);
		test.log(LogStatus.PASS, "Login successful - Test Passes");
	}

	  @AfterMethod
	  public void tearDown(ITestResult testResult) throws IOException {
		  if (testResult.getStatus()==ITestResult.FAILURE) {
			  String path = utilities.FailedTCScreenshot.ScreenShotMethod.
					  TakeScreenshot(wd, testResult.getName());
			  String imgPath = test.addScreenCapture(path);
			  test.log(LogStatus.FAIL, "Verification of TC failed", imgPath);
		  }
	  }
	  
	@AfterClass
	public void afterClass() throws Exception {
		Thread.sleep(2000);
		wd.quit();
		report.endTest(test);
		report.flush();
	}

}

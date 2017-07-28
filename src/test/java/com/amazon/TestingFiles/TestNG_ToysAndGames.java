package com.amazon.TestingFiles;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.Constants;
import utilities.reportPath;
import utilities.utility;

public class TestNG_ToysAndGames {
	WebDriver wd;
	utility search;
	ExtentReports report;
	ExtentTest test;
	
	  @BeforeClass
	  public void beforeClass() {
		  report = reportPath.getInstance();
		  test = report.startTest("Print out all page titles from Toys & Games (top tabs pages");
		  
//		  System.setProperty("webdriver.gecko.driver", "/Users/tatianakesler/Desktop/Selenium/installation/geckodriver");
		  
//		  wd = new FirefoxDriver();
//		  search = new utility(wd, test);
//		  
//		  wd.manage().window().maximize();
//		  wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
//		  
//		  wd.get(Constants.Url);
//		  
//		  test.log(LogStatus.INFO, "Browser started");
//		  test.log(LogStatus.INFO, "Browser maximized");
	  }
	  
	  @BeforeMethod
	  @Parameters({"browser"})
	  public void multipleBrowsers_SetUp(String browser){
		  if (browser.equalsIgnoreCase("firefox")){
			  System.setProperty("webdriver.gecko.driver", "/Users/tatianakesler/Desktop/Selenium/installation/geckodriver");
			  wd = new FirefoxDriver();
		  }
		  else if (browser.equalsIgnoreCase("chrome")){
			  System.setProperty("webdriver.chrome.driver", "/Users/tatianakesler/Desktop/Selenium/installation/chromedriver");
			  wd = new ChromeDriver();
		  }
         
		  search = new utility(wd, test);
		  
		  wd.manage().window().maximize();
		  wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		  
		  wd.get(Constants.Url);
		  
		  test.log(LogStatus.INFO, "Browser started");
		  test.log(LogStatus.INFO, "Browser maximized");
	  }
	
	@Test
  public void LoopThroughAllElementsTopPage() {
		search.clickDepartments();
		search.clickToysGames();
		search.ClickPrintOutAllTitlesInToys();

  
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
  public void afterClass() throws InterruptedException {
	  Thread.sleep(3000);
	  wd.quit();
	  report.endTest(test);
	  report.flush();
  }

}

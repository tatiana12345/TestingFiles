package utilities;

import java.util.ArrayList;
import java.util.Date;

import com.relevantcodes.extentreports.ExtentReports;

public class reportPath{
	
	public static String getDate(){
		Date d = new Date();
		String date = d.toString().replace(":", "."); 
		return date;
	}
	
	public static ExtentReports getInstance(){
		
		ArrayList<String> info = new ArrayList <String>();
		info.add("Selenium version");
		info.add("3.4.0");
		info.add("2.5.1");
		info.add("Platform");
		info.add("Mac");
		info.add("Windows");
		info.add("OS version");
		info.add("Sierra");
		info.add("Windows 10");
		
		ExtentReports extent;
		
		String ReportName = getDate() + ".html";
		String ReportPath = "/Users/tatianakesler/Desktop/reports/" + ReportName;
		
		extent = new ExtentReports(ReportPath, false);
		extent.addSystemInfo(info.get(0), info.get(1)).addSystemInfo(info.get(3), info.get(4))
		.addSystemInfo(info.get(6), info.get(7));
		
		return extent;
		
	}

}

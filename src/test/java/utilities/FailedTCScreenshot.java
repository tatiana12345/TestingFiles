package utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class FailedTCScreenshot {
	
	public static class ScreenShotMethod{
		
		public static String TakeScreenshot (WebDriver wd, String FileName) throws IOException{
			FileName = FileName+ ".png";
			String Directory = "/Users/tatianakesler/Desktop/test123/";
			File sourceFile = ((TakesScreenshot) wd).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File (Directory + FileName));
			String destination = Directory + FileName;
			
			return destination;
		}
		
	}

}

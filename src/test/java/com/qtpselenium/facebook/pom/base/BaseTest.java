package com.qtpselenium.facebook.pom.base;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.concurrent.TimeUnit;






import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import com.qtpselenium.facebook.pom.util.ExtentManager;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.qtpselenium.facebook.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BaseTest {
    public ExtentReports extent=ExtentManager.getInstance();
    public ExtentTest test;
    public Xls_Reader xls=new Xls_Reader(FBConstants.DATA_XLS_PATH);
	
	public WebDriver driver;
	
	public void init(String bType){
		test.log(LogStatus.INFO, "Opening browser : " +bType);
		if(!FBConstants.GRID_RUN){
		  if(bType.equals("Mozilla")){
			System.setProperty("webdriver.gecko.driver",FBConstants.GECKO_DRIVER_EXE_PATH);
			driver = new FirefoxDriver();
		   }else if(bType.equals("Chrome")){
			System.setProperty("webdriver.chrome.driver",FBConstants.CHROME_DRIVER_EXE_PATH);
			driver = new ChromeDriver();
		   }
		}else{
			//run on grid
			// grid
						DesiredCapabilities cap=null;
						if(bType.equals("Mozilla")){
							cap = DesiredCapabilities.firefox();
							cap.setBrowserName("firefox");
							cap.setJavascriptEnabled(true);
							cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
							
						}else if(bType.equals("Chrome")){
							 cap = DesiredCapabilities.chrome();
							 cap.setBrowserName("chrome");
							 cap.setPlatform(org.openqa.selenium.Platform.WINDOWS);
						}
						
						try {
							driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), cap);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		}
		System.out.println("**********************");
		System.out.println("Reading from Jenkins......"+System.getProperty("implicitWait"));
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "Opened browser successfully : " +bType);
		
	}
	
	public void takeScreenshot(){		
		Date d=new Date();
		String screenshotFile=d.toString().replace(":", "_").replace(" ", "_")+".png";
		String filePath=FBConstants.REPORTS_PATH+"screenshots//"+screenshotFile;
		// store screenshot in that file
		File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		try {
			FileUtils.copyFile(scrFile, new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		test.log(LogStatus.INFO,test.addScreenCapture(filePath));
	}
	
	public void reportFailure(String failureMessage){
		test.log(LogStatus.FAIL, "Failing the test - "+failureMessage);
		Assert.fail(failureMessage);
	}

}

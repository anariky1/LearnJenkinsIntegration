package com.qtpselenium.facebook.pom.base;

//to import automatically control+shift+o

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.qtpselenium.facebook.pom.common.TopMenu;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BasePage {
	
	public WebDriver driver;
	public ExtentTest test;
	public TopMenu menu;
	public WebDriverWait wait;

	public BasePage(){}
	
	public BasePage(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
		menu=new TopMenu(driver,test);
		PageFactory.initElements(driver, menu);
		
	}
	
	public String verifyTitle(String expTitle){
		test.log(LogStatus.INFO, "Verifying the title : " +expTitle);
		return "";
	}
	
	public String verifyText(String locator,String expText){
		return "";
	}
	
	
	public boolean isElementPresent(String locator){
		test.log(LogStatus.INFO, "Trying to find the element : " +locator);
		int s=driver.findElements(By.xpath(locator)).size();
		if(s==0){
			test.log(LogStatus.INFO, "Element not found " +locator);
		return false;
		}
		test.log(LogStatus.INFO, "Element found " +locator);
		return true;
	}
	
	public TopMenu getMenu(){
		return menu;
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
	
	public  void reportFailure(String failureMessage){
		takeScreenshot();
		test.log(LogStatus.FAIL, "Failing the test");
		Assert.fail(failureMessage);
	}
	
	public void waitUntilElementPresent(int time){
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.SECONDS);
		
	}
	
	public void explicitWait(String xpath , int timeUnit){
		wait = new WebDriverWait(driver,timeUnit);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))).isDisplayed();
	}

}



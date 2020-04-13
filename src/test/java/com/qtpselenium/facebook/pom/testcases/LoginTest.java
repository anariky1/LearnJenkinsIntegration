package com.qtpselenium.facebook.pom.testcases;

import java.awt.AWTException;
import java.util.Hashtable;

import org.openqa.selenium.support.PageFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qtpselenium.facebook.pom.base.BaseTest;
import com.qtpselenium.facebook.pom.mail.SendMail;
import com.qtpselenium.facebook.pom.mail.SendMailSSLWithAttachment;
import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.util.DataUtil;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.qtpselenium.facebook.pom.util.Xls_Reader;
import com.relevantcodes.extentreports.LogStatus;

public class LoginTest extends BaseTest {
	
	public String testCaseName="LoginTest";
	
	@Test(dataProvider="getData")
	public void doLogin(Hashtable<String,String>data) throws AWTException, InterruptedException{
		test=extent.startTest("Login Test");
		
		if(!DataUtil.isTestExecutable(xls, testCaseName)||data.get(FBConstants.RUNMODE_COL).equals("N")){
			test.log(LogStatus.SKIP, "Skipping the test case as Run Mode is N");
			throw new SkipException("Skipping the test case as Run Mode is N");
		}
		
		test.log(LogStatus.INFO,"Starting Login Test");
		test.log(LogStatus.INFO,"Opening browser");
		init(data.get("Browser"));
		
		LaunchPage launchPage=new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchPage);
		
		//LaunchPage launchpage=PageFactory.initElements(driver, LaunchPage.class);
		test.log(LogStatus.INFO,"Opening Facebook login page");
		LoginPage loginPage=launchPage.gotoLoginPage();
		test.log(LogStatus.INFO,"Logging in");
		Object page=loginPage.doLogin(data.get("Username"),data.get("Password"));
		String actualResult="";
		if(page instanceof LandingPage){
			actualResult="Success";
		}else{
			actualResult="Unsuccesful";
		}
		
		
		if(!actualResult.equals(data.get("ExpectedResult"))){
			loginPage.takeScreenshot();
			reportFailure("Could not login");
		}
		test.log(LogStatus.PASS,"Test passed");

	}
	
	@AfterMethod
	public void quit(){
	if(extent!=null){
		extent.endTest(test);
		extent.flush();	
	}
	
	if(driver!=null){
		driver.quit();
	}
	}
	
	@DataProvider
	public Object[][] getData(){
		return DataUtil.getData(xls, testCaseName);
		
	}
	
	@AfterSuite
	public void sendemail(){
	 SendMailSSLWithAttachment.sendEMail();
	test.log(LogStatus.INFO, "Emil sent sucessfully to the  recipients");
				
	}

	
	

}

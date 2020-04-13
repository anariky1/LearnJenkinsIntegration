package com.qtpselenium.facebook.pom.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.awt.AWTException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.SkipException;

import com.qtpselenium.facebook.pom.base.BaseTest;
import com.qtpselenium.facebook.pom.pages.LaunchPage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.pages.session.ProfilePage;
import com.qtpselenium.facebook.pom.util.DataUtil;
import com.qtpselenium.facebook.pom.util.ExtentManager;
import com.relevantcodes.extentreports.LogStatus;

public class ProfileTest extends BaseTest {
	
	String testCaseName="ProfileTest";
	
	@Test
	public void testProfile() throws AWTException, InterruptedException{
		test=extent.startTest("Profile Test");
		
		if(!DataUtil.isTestExecutable(xls, testCaseName)){
			test.log(LogStatus.SKIP, "Skipping the test as Rnumode is N");
			throw new SkipException("Skipping the test as Rnumode is N");
		}
		
		test.log(LogStatus.INFO,"Starting Profile Test");
		test.log(LogStatus.INFO,"Opening browser");
		init("Mozilla");
		LaunchPage launchpage=new LaunchPage(driver,test);
		PageFactory.initElements(driver, launchpage);
		LoginPage loginPage=launchpage.gotoLoginPage();
		loginPage.verifyTitle("Facebook login");
		Object page=loginPage.doLogin("friendlyananth15@gmail.com","");
		
		if(page instanceof LoginPage){
			Assert.fail("Login failed");
		}else if(page instanceof LandingPage){	
		System.out.println("Logged in successfully");
		LandingPage landingPage=(LandingPage) page;	
		//landingPage.getMenu().search();
		//landingPage.verifyTitle("xxx");
		ProfilePage profilePage=landingPage.gotoProfilePage();
		profilePage.verifyProfile();
		test.log(LogStatus.PASS, "Profile Test Passed");
		Thread.sleep(1000);
		profilePage.takeScreenshot();
		
		
		//profilePage.getMenu().logOut();
		
		
		}
		
		
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

}

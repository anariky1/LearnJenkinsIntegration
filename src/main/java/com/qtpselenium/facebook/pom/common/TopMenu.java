package com.qtpselenium.facebook.pom.common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.pages.session.GeneralSettingsPage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;



public class TopMenu extends BasePage{
	
	@FindBy(xpath=FBConstants.NAVIGATION_LABEL)
	public WebElement navigationLabel;
	
	@FindBy(xpath=FBConstants.SETTINGS_LINK)
	public WebElement settingsLink;
	
	public WebDriver driver;
	ExtentTest test;
	
	public TopMenu(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
	
	}
	
	
	public void logOut(){
		
	}
	
	public GeneralSettingsPage gotToSettings(){
		/*test.log(LogStatus.INFO, "Going to Settings");
		navigationLabel.click();
		settingsLink.click();
		test.log(LogStatus.INFO, "Settings page opened");*/
		
		test.log(LogStatus.INFO, "Going to settings");
		//navigationLabel.click();
		JavascriptExecutor js = (JavascriptExecutor)driver;
		js.executeScript("document.getElementById('userNavigationLabel').click()");
		settingsLink.click();
		test.log(LogStatus.INFO, "Settings page opened");
		GeneralSettingsPage settings =new GeneralSettingsPage(driver,test);
		PageFactory.initElements(driver, settings);
		return settings;
	}
	
	public void search(){
		
	}

}

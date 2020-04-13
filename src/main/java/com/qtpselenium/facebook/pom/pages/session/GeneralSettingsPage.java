package com.qtpselenium.facebook.pom.pages.session;

import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class GeneralSettingsPage extends BasePage {
	
	@FindBy(xpath=FBConstants.SECURITY_LOGIN)
	public WebElement securityLoginLink;
	
	@FindBy(xpath=FBConstants.CHANGE_PASSWORD)
	public WebElement changePassword;
	
	@FindBy(xpath=FBConstants.OLD_PASSWORD)
	public WebElement oldPasswordele;
	
	@FindBy(xpath=FBConstants.NEW_PASSWORD)
	public WebElement newPasswordele;
	
	@FindBy(xpath=FBConstants.RECONFIRM_PASSWORD)
	public WebElement reConfirmPasswordele;
	
	@FindBy(xpath=FBConstants.SAVE_BTN_CHANGEPASSWORD)
	public WebElement savebtnChangePassword;
	
	@FindBy(xpath=FBConstants.PASSWORD_CHANGE_CONFIRMATION_MESSAGE)
	public WebElement passwordChangeConfirmationMessage;
	
	@FindBy(xpath=FBConstants.STAY_LOGGED_IN)
	public WebElement keepSessions;
	
	@FindBy(xpath=FBConstants.CONTINUE_PASSWORD_CHANGE_BUTTON)
	public WebElement paaswordChangeContinueBtn ;
	
	
	
	
	
	public GeneralSettingsPage(WebDriver driver,ExtentTest test){
		this.driver=driver;
		this.test=test;
	}
	
	public void gotoPasswordChange(){
		//go to security and login
		test.log(LogStatus.INFO, "Clicking on Security and Login Link");
		if(!isElementPresent(FBConstants.SECURITY_LOGIN)){
			reportFailure("Security and Login link not present");
			
		}
		securityLoginLink.click();
		test.log(LogStatus.INFO, "Clicked on Security and Login Link");
		//clicking on change password
		test.log(LogStatus.INFO, "Clicking on Change Password section");
		if(!isElementPresent(FBConstants.CHANGE_PASSWORD)){
			reportFailure("Change Password section not present");
			
		}
		changePassword.click();
		test.log(LogStatus.INFO, "On Change Password section");
		
		
		
	}

	public String doPasswordChange(String oldPassword,String newPassword) {
		oldPasswordele.sendKeys(oldPassword);
		newPasswordele.sendKeys(newPassword);
		reConfirmPasswordele.sendKeys(newPassword);
		savebtnChangePassword.click();
		//if(!isElementPresent(FBConstants.PASSWORD_CHANGE_CONFIRMATION_MESSAGE)){
		
		Set<String>winIds=driver.getWindowHandles();
		System.out.println("Total windows opened"+ winIds.size());
		
		if(!isElementPresent(FBConstants.STAY_LOGGED_IN)){
			return "UnSuccessful";
		}
		keepSessions.click();
		paaswordChangeContinueBtn.click();
		test.log(LogStatus.INFO, "Changed Password Successfully");
		return "Success";
		
		
		
		
		
		
	}

}

package com.qtpselenium.facebook.pom.pages;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.qtpselenium.facebook.pom.pages.session.LandingPage;
import com.qtpselenium.facebook.pom.util.FBConstants;

public class LoginPage extends BasePage {
	
	
	public Robot r;
	
	
	@FindBy(xpath=FBConstants.LOGIN_USERNAME)
	public WebElement username;
	
	@FindBy(xpath=FBConstants.LOGIN_PASSWORD)
	public WebElement password;
	
	@FindBy(xpath="//input[@value='Log In']")
	public WebElement submitbtn;
	
	
	public LoginPage(WebDriver driver ,ExtentTest test){
		super(driver,test);
	}
	
	public Object doLogin(String UserName , String Password) throws AWTException, InterruptedException{
		test.log(LogStatus.INFO, "Entering the Username : "+UserName);
		username.sendKeys(UserName);
		test.log(LogStatus.INFO, "Entering the Password : "+Password);
		password.sendKeys(Password);
		Thread.sleep(1000);
		test.log(LogStatus.INFO, "Clicking the submit button");
		
		
		
		submitbtn.click();
		//Actions act = new Actions(driver);
		//act.moveToElement(submitbtn).click();
	    //canceling the notification 		
		r= new Robot();
		r.keyPress(KeyEvent.VK_ESCAPE);
		r.keyRelease(KeyEvent.VK_ESCAPE);
		
		boolean loginSuccess=isElementPresent(FBConstants.PROFILEPAGE_LINK);
		
		
		if(loginSuccess){
			test.log(LogStatus.INFO, "Login Success");
			LandingPage landingPage=new LandingPage(driver,test);
			PageFactory.initElements(driver, landingPage);
			return landingPage ;
		}else{
			test.log(LogStatus.INFO, "Login Not Success");
			LoginPage loginPage=new LoginPage(driver,test);
			PageFactory.initElements(driver, loginPage);
			return loginPage ;	
		}
				
	}

}

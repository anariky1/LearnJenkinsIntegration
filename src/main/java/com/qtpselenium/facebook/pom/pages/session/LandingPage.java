package com.qtpselenium.facebook.pom.pages.session;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qtpselenium.facebook.pom.base.BasePage;
import com.qtpselenium.facebook.pom.pages.LoginPage;
import com.qtpselenium.facebook.pom.util.FBConstants;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class LandingPage extends BasePage{
	
	
	@FindBy(xpath=FBConstants.PROFILEPAGE_LINK)
	public WebElement profileLink;
	
	public LandingPage(WebDriver driver,ExtentTest test){
		super(driver,test);
	}
	
	public ProfilePage gotoProfilePage(){
		test.log(LogStatus.INFO, "Going to profile page");
		profileLink.click();
		ProfilePage profilePage=new ProfilePage(driver,test);
		PageFactory.initElements(driver, profilePage);
		return profilePage ;	
		
	}
	public boolean sendChatMessage(String chatFriend) throws InterruptedException{
	 try{
		System.out.println(chatFriend);
		/*WebElement ele=driver.findElement(By.xpath("//div[@data-testid='chat_sidebar']"));
		List<WebElement> friends=ele.findElements(By.tagName("li"));
		for(int i=0;i<=friends.size()-1;i++){
			 System.out.println(friends.get(i).getText());
		}*/
		
		// WebElement webl = driver.findElement(By.xpath("//div[@data-contents='true']"));
        //JavascriptExecutor js = (JavascriptExecutor)driver; 
        //js.executeScript("arguments[0].value='Avinash Mishra';", webl);
        
        //((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', 'test')", webl);
		
		List<WebElement> friends = driver.findElements(By.xpath("//div[@class='_55lr']"));
		System.out.println(friends.size());
		for(int i=0;i<=friends.size()-1;i++){
			 System.out.println(friends.get(i).getText());
			 if(friends.get(i).getText().equals("Create New Group")){
				 friends=driver.findElements(By.xpath("//div[@class='_55lr']"));
			 }
			 
			 if(friends.get(i).getText().equals(chatFriend)){
				 friends.get(i).click();
			 }
		}
		
		
		explicitWait("//div[@aria-autocomplete and @role='combobox1']", 30);
		
		System.out.println("chat window opened...");
		Set<String>winIds=driver.getWindowHandles();
		System.out.println("Total windows opened"+ winIds.size());
		
		driver.findElement(By.xpath("//div[@aria-autocomplete and @role='combobox']")).sendKeys("hi..sent from automation script");
		waitUntilElementPresent(1000);
	    driver.findElement(By.xpath("//div[@aria-autocomplete and @role='combobox']")).sendKeys(Keys.ENTER);
	    waitUntilElementPresent(1000);

       
        
        
		System.out.println("test....");
		
	 }catch(Exception e){
		 System.out.println("unable to send chat message");
		 return false;
	 }
	
	return true;
}

}

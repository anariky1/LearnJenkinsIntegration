package com.qtpselenium.facebook.pom.util;

public class FBConstants {
	
	public static final boolean GRID_RUN=false;
	
	public static final String CHROME_DRIVER_EXE_PATH=System.getProperty("user.dir")+"\\chrome_driver\\chromedriver.exe";	
	//public static final String GECKO_DRIVER_EXE_PATH="E:\\geckodriver-v0.19.1-win64\\geckodriver.exe";
	public static final String GECKO_DRIVER_EXE_PATH="E:\\geckodriver-v0.21.0-win64\\geckodriver.exe";
	
	//LOCATORS
	public static final String LOGIN_USERNAME = "//input[@id='email']";
	public static final String LOGIN_PASSWORD = "//input[@id='pass']";
	//public static final String PROFILEPAGE_LINK = "//span[@class='_1qv9']/span[@class='_1vp5 f_click']";
	public static final String PROFILEPAGE_LINK = "//span[@class='_1qv9']/span";
	public static final String NAVIGATION_LABEL = "//div[@id='userNavigationLabel']";
	public static final String SETTINGS_LINK ="//span[text()='Settings']";
	public static final String SECURITY_LOGIN = "//a[@title='Security and Login']";
	public static final String CHANGE_PASSWORD = "//span[text()='Change password']"	;
	public static final String OLD_PASSWORD = "//input[@id='password_old']";
	public static final String NEW_PASSWORD = "//input[@id='password_new']";
	public static final String RECONFIRM_PASSWORD = "//input[@id='password_confirm']";
	public static final String SAVE_BTN_CHANGEPASSWORD = "//input[@value='Save Changes']";
	public static final String PASSWORD_CHANGE_CONFIRMATION_MESSAGE = "//h3[@text()='Password Changed']";
	public static final String STAY_LOGGED_IN = "//*[text()='Stay logged in']/preceding::span[1]";
	public static final String CONTINUE_PASSWORD_CHANGE_BUTTON = "//button[text()='Continue']";
	
	
	
	//URLS
	public static final String HOMEPAGE_URL = "http://facebook.com";
	
	//PATHS
	public static final String REPORTS_PATH = System.getProperty("user.dir")+"\\reports";
	public static final String DATA_XLS_PATH=System.getProperty("user.dir")+"\\data\\Data.xlsx";
	public static final String TESTDATA_SHEET = "TestData";
	
	
	
	public static final Object RUNMODE_COL = "RunMode";
	public static final String TESTCASE_SHEET = "TestCases";
	
	
	
	
	
	
	

}

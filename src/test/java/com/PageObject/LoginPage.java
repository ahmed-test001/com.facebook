package com.PageObject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	WebDriver ldriver;
	
	public LoginPage(WebDriver rdriver)
	{ 
	ldriver=rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	@CacheLookup@FindBy(xpath="//input[@id='email']") WebElement TxtBoxEmail;	
	@CacheLookup@FindBy(xpath="//input[@id='pass']") WebElement TxtBoxPass;		
	@CacheLookup@FindBy(xpath="//label[@id='loginbutton']")WebElement BtnLogin;
	@CacheLookup@FindBy(xpath="//button[@name='login']")WebElement BtnLogin2;

	
	public void setEmail(String uname)
	{
		TxtBoxEmail.sendKeys(uname);		
	}
	
	public void setPassword(String pwd)
	{
		TxtBoxPass.sendKeys(pwd);
	}

	public void clickLoginBtn() {
		try {
			BtnLogin.click();
			System.out.println("Button 1");
		} catch (NoSuchElementException e) {
			BtnLogin2.click();
			System.out.println("Button 2 \n" + e.getMessage());
		}
	}
	
}

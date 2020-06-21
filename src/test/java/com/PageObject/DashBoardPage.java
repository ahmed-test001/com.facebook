package com.PageObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage {
	
	WebDriver ldriver;
	
	public DashBoardPage(WebDriver rdriver)
	{ 
	ldriver=rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	@CacheLookup@FindBy(xpath="(//a[contains(@href,'marketplace')])[1]") WebElement BtnMrktPlc;	


	
	public void getMrktPl()
	{
		BtnMrktPlc.click();		
	}
	


}
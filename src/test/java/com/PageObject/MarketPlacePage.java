package com.PageObject;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class MarketPlacePage {
	
	WebDriver ldriver;
	
	public MarketPlacePage(WebDriver rdriver)
	{ 
	ldriver=rdriver;
	PageFactory.initElements(rdriver, this);
	}
	
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Create New Listing')]") WebElement BtnCreatNwLink;	
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Item for Sale')]")WebElement BtnItmSell;
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Add Photos')]")WebElement BtnAddPhotos;
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Title')]/following-sibling::input")WebElement TextBoxTitle;
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Price')]/following-sibling::input")WebElement TextBoxPrice;
	@CacheLookup@FindBy(xpath="//span[contains(.,'Category')]/following-sibling::div")WebElement TextBoxCatagory;
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Men')]")WebElement TextBoxMensClothing;
	@CacheLookup@FindBy(xpath="//span[contains(.,'Condition')]/following-sibling::div")WebElement TextBoxCondition;
	@CacheLookup@FindBy(xpath="//div[@class='l9j0dhe7']/descendant::span[text()='New']")WebElement TextBoxNew;
	@CacheLookup@FindBy(xpath="//textarea[starts-with(@id,'jsc_c')]")WebElement TextBoxDescription;
	@CacheLookup@FindBy(xpath="//span[contains(.,'Location')]/following-sibling::input")WebElement InputLocation;
	@CacheLookup@FindBy(xpath="//span[contains(.,'Availability')]/following-sibling::div")WebElement Availability;
	@CacheLookup@FindBy(xpath="//span[contains(text(),'List as Single Item')]")WebElement ListAsSingleItem;
	@CacheLookup@FindBy(xpath="//span[contains(text(),'Publish')]")WebElement Publish;
	@CacheLookup@FindBy(xpath="(//div[@aria-label='More'])[1]")WebElement MoreBtn;
	@CacheLookup@FindBy(xpath="//div[@role='menu']/descendant::span[contains(text(),'Delete')]")WebElement DeletetBtn;
	@CacheLookup@FindBy(xpath="(//div[@aria-label='Delete Listing' and contains(.,'Delete')]/descendant::span[text()='Delete'])[1]")WebElement DeletetConfirm;
	
	@CacheLookup By ListVerification = By.xpath("(//div[@class='tvmbv18p'])[1]/child::div/child::div");

	public void createMrktPlList()
	{
		BtnCreatNwLink.click();		
	}
	
	public void selectSellItm()
	{
		BtnItmSell.click();;
	}
	
	public void BtnAddPhotos()
	{
		BtnAddPhotos.click();			
	}
	
	public void setProdTitle(String Title)
	{	
		TextBoxTitle.sendKeys(Title);
	}
	
	public void setProdPrice(String ProdName)
	{
		TextBoxPrice.sendKeys(ProdName);
	}
	
	public void setProdDescription(String ProdDesc)
	{
		TextBoxDescription.sendKeys(ProdDesc);
	}

	public void selectCategory() throws Exception
	{
		CommonClass.js.executeScript("arguments[0].scrollIntoView(true);", TextBoxCatagory);
		Thread.sleep(2000);
		TextBoxCatagory.click();
	}
	public void selectMensClothing() throws Exception
	{
		CommonClass.js.executeScript("arguments[0].scrollIntoView(true);", TextBoxMensClothing);
		Thread.sleep(2000);
		TextBoxMensClothing.click();			
	}
	
	public void selectCondition() throws Exception
	{
		CommonClass.js.executeScript("arguments[0].scrollIntoView(true);", TextBoxCondition);
		Thread.sleep(2000);
		TextBoxCondition.click();
	}
	
	public void selectNew()
	{
		TextBoxNew.click();			
	}
	
	public void setLocation(String location) throws Exception
	{
		CommonClass.js.executeScript("arguments[0].scrollIntoView(true);", InputLocation);
		Thread.sleep(2000);
		InputLocation.click();
		Thread.sleep(2000);
		InputLocation.sendKeys(location);
		Thread.sleep(2000);
		InputLocation.sendKeys(Keys.ARROW_DOWN);
		InputLocation.sendKeys(Keys.ENTER);
	}
	
	public void selectAvailability() throws Exception
	{
		Availability.click();
		Thread.sleep(2000);
		ListAsSingleItem.click();
	}
	
	public void publishItem()
	{
		Publish.click();			
	}
	
	public List<WebElement> listVerification(){
		List<WebElement> list = CommonClass.driver.findElements(ListVerification);
		return list;
	}
	
	public void selectMore()
	{
		MoreBtn.click();			
	}
	
	public void deleteBtn()
	{
		DeletetBtn.click();			
	}
	
	public void ConfirmDelete()
	{
		DeletetConfirm.click();			
	}
	
	public boolean VerifyTitle(String title) {
		try {
			CommonClass.driver.findElement(By.xpath("//*[text()='"+title+"']"));
			System.out.println(CommonClass.driver.findElement(By.xpath("//*[text()='"+title+"']")).getText());
			return false;
		}catch (NoSuchElementException e) {
			return true;
		}
	}
}

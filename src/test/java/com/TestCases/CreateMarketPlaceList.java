package com.TestCases;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.PageObject.CommonClass;
import com.PageObject.Constants;
import com.PageObject.DashBoardPage;
import com.PageObject.LoginPage;
import com.PageObject.MarketPlacePage;

public class CreateMarketPlaceList extends CommonClass {

	LoginPage lp;
	DashBoardPage dbp;
	MarketPlacePage mp;

	@BeforeClass
	public void init() {
		lp = new LoginPage(driver);
		dbp = new DashBoardPage(driver);
		mp = new MarketPlacePage(driver);
	}

	@Test
	public void createMrkPltList() throws Exception {
		try {
			lp.setEmail(username);
			logger.info("User Name has been entered");
			lp.setPassword(password);
			logger.info("Password has been entered");
			lp.clickLoginBtn();
			logger.info("Logging in...");
			Thread.sleep(5000);
			String titel = driver.getTitle();
			Assert.assertEquals(driver.getTitle(), titel);
			dbp.getMrktPl();
			logger.info("Clicking Market Place");
			Thread.sleep(5000);
			String titleX = driver.getTitle();
			titel = titleX.substring(titleX.indexOf("F"), titleX.length());
			Assert.assertEquals(Constants.title, titel);
			WindowHandle();
			mp.createMrktPlList();
			logger.info("Listing an item");
			mp.selectSellItm();
			logger.info("Item for Sell");
			mp.BtnAddPhotos();
			AddPhotos();
			Thread.sleep(5000);
			logger.info("Adding Item Photo");
			mp.setProdTitle(Constants.itemTitle);
			logger.info("Setting Item title as : "+ Constants.itemTitle);
			mp.setProdPrice(Constants.itemPrice);
			logger.info("Setting Item price as : "+ Constants.itemPrice);
			mp.selectCategory();
			logger.info("Selecting Category");
			Thread.sleep(2000);
			mp.selectMensClothing();
			Thread.sleep(1000);
			mp.selectCondition();
			logger.info("Setting condition for Item");
			Thread.sleep(2000);
			mp.selectNew();
			Thread.sleep(1000);
			mp.setProdDescription(Constants.desc);
			logger.info("Adding item description as : " + Constants.desc);
			Thread.sleep(1000);
			mp.setLocation("New York");
			logger.info("Setting Location");
			mp.selectAvailability();
			Thread.sleep(1000);
			mp.publishItem();
			logger.info("Publishing Listing in Market place .......");
			Thread.sleep(5000);
			String actualTitel = mp.listVerification().get(0).getText();
			String actualPrice = mp.listVerification().get(1).getText();
			logger.info("Verifying item title and price \n Actual Item Title is : "+ actualTitel + " vs. Expected Title is : " + Constants.itemTitle);
			logger.info("\n Actual Item Price is : "+ actualPrice + " vs. Expected Price is : $" + Constants.itemPrice);
			Assert.assertEquals(actualTitel, Constants.itemTitle);
			Assert.assertEquals(actualPrice, "$" + Constants.itemPrice);
			mp.selectMore();
			logger.info("Deleting Item :: " + Constants.itemTitle);
			Thread.sleep(2000);
			mp.deleteBtn();
			Thread.sleep(2000);
			mp.ConfirmDelete();
			Thread.sleep(2000);
			logger.info("Verifying item is deleted...");
			Assert.assertTrue(mp.VerifyTitle(Constants.itemTitle));
			logger.info("Item has been deleted - Verified");
		} catch (Exception e) {
			captureScreen(driver, "createMrkPltList-Error");
			Assert.assertTrue(false);
			System.out.println(e.getMessage());
			logger.info("Error Occure during execution. Please find the Error/Exception below \n"+e.getMessage());
			Assert.fail(e.getMessage());
		}

	}

}

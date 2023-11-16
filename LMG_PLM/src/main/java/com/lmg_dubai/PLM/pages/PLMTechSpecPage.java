package com.lmg_dubai.PLM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMTechSpecPage extends BasePage{

	public PLMTechSpecPage(WebDriver driver) {
		super(driver);
	}

	//Element to identify create tech spec button
	@FindBy(xpath = "//td[@id='CREATE_TECH_SPECbtnCtr']")
	private WebElement createTechSpecBtn;

	//Element to identify hidden tabs expand button
	@FindBy(xpath = "//div[@id='Tabs']//tr[@id='Tabsrow']//td[@id='More']//label[contains(text(),'>>')]")
	private WebElement hiddenTabsExpandBtn;

	private static String visibleTopSectionTabsXpath="//div[@id='Tabs']//tr[@id='Tabsrow']//td//label[contains(text(),'$visibleTabOption')]";
	private static String hiddenTopSectionTabsXpath="//div[@id='divChildActBtns_More']//tr//td//label[contains(text(),'$hiddenTabOption')]";



	public void clickOnCreateTechSpecBtn() throws Exception {
		GenericLib.explicitWait(driver, "//td[@id='CREATE_TECH_SPECbtnCtr']");
		BrowserActionUtil.clickElement(createTechSpecBtn, driver, "Create Tech Spec Button");
		GenericLib.explicitWait(driver, "//td[@id='TECHSPEC_OVERVIEW_SAVEbtnCtr']");
	}
	
	public void clickOnTopTabByName(String tabToSelect) throws Exception {
		try {
			String visibleTabXpath = visibleTopSectionTabsXpath.replace("$visibleTabOption", tabToSelect);
			WebElement requiredTab = driver.findElement(By.xpath(visibleTabXpath));
			BrowserActionUtil.clickElement(requiredTab, driver, tabToSelect+" Tab");
			Thread.sleep(3000);
		} catch (Exception e) {
			BrowserActionUtil.clickElement(hiddenTabsExpandBtn, driver, "Tabs Expand Button");
			String hiddenTabXpath = hiddenTopSectionTabsXpath.replace("$hiddenTabOption", tabToSelect);
			WebElement hiddenTab= driver.findElement(By.xpath(hiddenTabXpath));
			BrowserActionUtil.clickElement(hiddenTab, driver, tabToSelect+" Tab");
			Thread.sleep(3000);
		}
	}
	
	public void clickRecentDocument(String index) throws Exception {
		GenericLib.explicitWait(driver, "//div[text()='Recently Viewed TechSpecs']/following-sibling::div//a[text()='* Style No - 048 TB24JUL23-2 TESTING']");
		WebElement recentLink = driver.findElement(By.xpath("(//div[text()='Recently Viewed TechSpecs']/following-sibling::div//a[text()='* Style No - 048 TB24JUL23-2 TESTING'])["+index+"]"));
		BrowserActionUtil.clickElement(recentLink, driver, "Recent Link");
	}
}

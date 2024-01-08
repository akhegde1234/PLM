package com.lmg_dubai.PLM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMDashboardPage extends BasePage{

	public PLMDashboardPage(WebDriver driver) {
		super(driver);
	}
	
	
	private static String dashboardXpath="//div[@id='listElementsContainer']//div[@class='dashTitle' and  text()='$dashboardTitle']/following-sibling::div[@class='dashInner']";
	private static String dashboardRefreshButton="//div[@class='dashTitle' and text()='$dashboardTitle']/following-sibling::div[@id='refreshlist']//a[@alt='Refresh Query Group']";
	private static String refreshDateAndTimeStamp="//div[@class='dashTitle' and text()='$dashboardTitle']/following-sibling::div[@id='refreshlist' and contains(text(),'Last Refreshed')]";
	private static String dashboardAnchorBtn="//div[@class='dashTitle' and text()='$dashboardTitle']/..//div[@class='dashInner']//a//span[text()='$anchorText']";
	private static String quickSearchOption="//ul[@id='quickSearchDocument_listbox']//li[@role='option' and text()='$optionName']";
	
	public void waitAndScrollToParticularDashboard(String dashboardTitleText) throws Exception {
		GenericLib.explicitWait(driver, "//div[@id='listElementsContainer']");
		String dashboard = dashboardXpath.replace("$dashboardTitle", dashboardTitleText);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[@class='dashTitle' and text()='Message']")).click();
		WebElement dashboardElement = driver.findElement(By.xpath(dashboard));
		BrowserActionUtil.scrollDownToElementAndClickOnTargetByActionsArrowDown(driver, dashboardElement, "Dashboard Title: "+dashboardTitleText);
		System.out.println("Dashboard Header Xpath: "+dashboard);
		MyExtentListeners.test.info("Dashboard Contents Include: "+dashboardElement.getText());
	}
	
	public void clickOnRefreshButton(String dashboardTitleText) throws Exception {
		String refreshBtnXpath = dashboardRefreshButton.replace("$dashboardTitle", dashboardTitleText);
		WebElement refreshBtn = driver.findElement(By.xpath(refreshBtnXpath));
		BrowserActionUtil.clickElement(refreshBtn, driver, "Refresh Button:"+dashboardTitleText);
		String refreshDateTimeStamp = refreshDateAndTimeStamp.replace("$dashboardTitle", dashboardTitleText);
		GenericLib.explicitWait(driver,refreshDateTimeStamp);
		MyExtentListeners.test.info("Last Refresh Time is: "+driver.findElement(By.xpath(refreshDateTimeStamp)).getText().trim());
	}
	
	public void clickOnAnchorTextOptionInDasboard(String anchorTextToClick) throws Exception {
		String reqdAnchorOptionXpath="//a[contains(text(),'$anchorText')]";
		String anchorBtn = reqdAnchorOptionXpath.replace("$anchorText", anchorTextToClick);
		System.out.println("Anchor Text Xpath is: "+anchorBtn);
		Thread.sleep(2000);
		WebElement requiredAnchorBtn = driver.findElement(By.xpath(anchorBtn));
		BrowserActionUtil.scrollToElement(driver, requiredAnchorBtn);
		BrowserActionUtil.clickElement(requiredAnchorBtn, driver, "Dashboard Anchor Button: "+anchorTextToClick);
	}
	
	public void clickOnTerritoryQtyAllocReg() throws Exception {
	 WebElement terrQtyAlloc = driver.findElement(By.xpath("//div[@class='dashTitle' and text()='Pre-Order Tasks']/parent::div//a//span[text()='Territory Quantity Allocn REG']"));
	 BrowserActionUtil.scrollToElement(driver, terrQtyAlloc);
	 BrowserActionUtil.clickElement(terrQtyAlloc, driver, "Territory Qty Alloc REG");
	}
	
	public void clickOnOrderBuilder() throws Exception {
		WebElement orderBuilder = driver.findElement(By.xpath("//div[@class='dashTitle' and text()='Business Process']/parent::div//a[contains(text(),'Order Builder')]"));
		BrowserActionUtil.scrollToElement(driver, orderBuilder);
		BrowserActionUtil.clickElement(orderBuilder, driver, "Order Builder");
	}
	
	public void quickSearchUsingOptionAndText(String dropdownOptionName, String searchText) throws Exception {
		GenericLib.explicitWait(driver, "//span[@aria-owns='quickSearchDocument_listbox']");
		WebElement listBox = driver.findElement(By.xpath("//span[@aria-owns='quickSearchDocument_listbox']"));
		listBox.click();
		String requiredOptionXpath = quickSearchOption.replace("$optionName", dropdownOptionName);
		WebElement option = driver.findElement(By.xpath(requiredOptionXpath));
		BrowserActionUtil.scrollToElement(driver, option);
		option.click();
		MyExtentListeners.test.pass("User selected quick search option: "+dropdownOptionName);
		WebElement quickSearchInputField = driver.findElement(By.xpath("//input[@id='quickSearchInput']"));
		BrowserActionUtil.clickElement(quickSearchInputField, driver, "Quick Search Input");
		BrowserActionUtil.clearAndType(quickSearchInputField, searchText, "Quick Search Input", driver);
		WebElement goBtn = driver.findElement(By.xpath("//a[@id='Gbl_Search_Btn']/preceding::input[@value='Go' and @type='button']"));
		BrowserActionUtil.clickElement(goBtn, driver, "Go Button");
		Thread.sleep(5000);
	}
}

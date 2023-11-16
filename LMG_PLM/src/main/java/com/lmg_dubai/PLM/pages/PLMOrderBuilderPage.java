package com.lmg_dubai.PLM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMOrderBuilderPage extends BasePage{

	public PLMOrderBuilderPage(WebDriver driver) {
		super(driver);
	}
	
	private static String searchLabelDropdown="//table[contains(@class,'searchSection')]//tr//td[text()='$labelName']/following::select";
	private static String searchLabelInputField="//table[contains(@class,'searchSection')]//tr//td[text()='$labelName']/following::input";
	
	public void handleSelectDropdownAndEnterSearchText(String labelName, String dropdownOption, String searchText) throws Exception{
		String searchLabelDropdownXpath = searchLabelDropdown.replace("$labelName", labelName);
		GenericLib.explicitWait(driver, searchLabelDropdownXpath);
		WebElement selectDropdown = driver.findElement(By.xpath(searchLabelDropdownXpath));
		BrowserActionUtil.clickElement(selectDropdown, driver, labelName+" Dropdown");
		BrowserActionUtil.handleSelectDropdown(driver, selectDropdown, dropdownOption);
		String searchTextFieldXpath = searchLabelInputField.replace("$labelName", labelName);
		WebElement searchTextFld = driver.findElement(By.xpath(searchTextFieldXpath));
		BrowserActionUtil.clickElement(searchTextFld, driver, labelName+" Search Field");
		BrowserActionUtil.clearAndType(searchTextFld, searchText, labelName+" Search Field", driver);
	}
	
	public void clickOnSearchButton() throws Exception {
		WebElement searchBtn = driver.findElement(By.xpath("//label[text()='Search']"));
		BrowserActionUtil.clickElement(searchBtn, driver, "Search Button");
		GenericLib.explicitWait(driver, "//table[@id='LDATA_TABLE']");
	}
	
	public void clickOnAddAllItemsButtonAndBuild() throws Exception {
		WebElement addAllItemsBtn = driver.findElement(By.xpath("//div[@alt='Add All']"));
		BrowserActionUtil.clickElement(addAllItemsBtn, driver, "Add All Items Button");
		WebElement buildBtn = driver.findElement(By.xpath("//a[@title='Build']"));
		BrowserActionUtil.scrollToElement(driver, buildBtn);
		BrowserActionUtil.clickElement(buildBtn, driver, "Build Button");
		GenericLib.explicitWaitForTime(driver, "//td//input[@id='0_@300_@3_@0_@0_@0']",120);//Work Order No. or Purchase Order
	}
	
	
}

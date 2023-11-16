package com.lmg_dubai.PLM.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.listeners.MyExtentListeners;

public class PLMOrderManagementOverviewPage extends BasePage{

	public PLMOrderManagementOverviewPage(WebDriver driver) {
		super(driver);
	}
	
	public String getPurchaseNumber() {
		WebElement purchaseNo = driver.findElement(By.xpath("//td//input[@id='0_@300_@3_@0_@0_@0']"));
		MyExtentListeners.test.pass("Purchase Number is: "+purchaseNo.getAttribute("value"));
		return purchaseNo.getAttribute("value");
	}
}

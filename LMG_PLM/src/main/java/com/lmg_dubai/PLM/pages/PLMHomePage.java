package com.lmg_dubai.PLM.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.lmg_dubai.PLM.library.BasePage;
import com.lmg_dubai.PLM.library.GenericLib;
import com.lmg_dubai.PLM.util.BrowserActionUtil;

public class PLMHomePage extends BasePage{

	public PLMHomePage(WebDriver driver) {
		super(driver);
	}

	
	public void clickOnNavigationHeader(String navHeaderText) throws Exception {//(Ex-DESIGN CENTER)
		List<WebElement> allSideNavHeaders = driver.findElements(By.xpath("//div[@class='navTitle']"));
		BrowserActionUtil.clickOnParticularTextOptionInElementsList(allSideNavHeaders, navHeaderText, driver);
		Thread.sleep(3000);
	}
	
	public void clickOnNavigationSubHeader(String navSubHeaderText) throws Exception {//(Ex-DESIGN CENTER DASHBOARD)
		List<WebElement> allSideNavSubHeaders = driver.findElements(By.xpath("//table//a[@class='navSubTitle']"));
		BrowserActionUtil.clickOnParticularTextOptionInElementsList(allSideNavSubHeaders, navSubHeaderText, driver);
		Thread.sleep(3000);
	}
	
	public void clickOnNavigationDashboardTextOption(String dashboardText) throws Exception {//(Ex-Dashboard, Artwork Library...)
		List<WebElement> allDashboardTextOptions = driver.findElements(By.xpath("//table//a[@class='clsDashAnchor']"));
		BrowserActionUtil.clickOnParticularTextOptionInElementsList(allDashboardTextOptions, dashboardText, driver);
		Thread.sleep(3000);
	}

}
